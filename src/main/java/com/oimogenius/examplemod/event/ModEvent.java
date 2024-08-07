package com.oimogenius.examplemod.event;

import com.oimogenius.examplemod.ExampleMod;
import com.oimogenius.examplemod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class ModEvent {
    // ブロックが破壊されたときに呼び出されるイベント
    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent event) {
        if (event.isCanceled()) {
            return;
        }

        LevelAccessor level = event.getLevel();
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        // アイテムに範囲破壊エンチャントがついている、かつ、
        // クリエイティブモードではない場合、処理を実行する
        if (mainHandItem.getEnchantmentLevel(ModEnchantments.AREA_BREAKER.get()) > 0
                && !player.isCreative()) {
            BlockPos pos = event.getPos();
            Direction direction = player.getMotionDirection();
            int xStart = direction.getAxis() == Direction.Axis.X ? (direction == Direction.EAST ?
                    0 : -2) : -1;
            int xEnd = direction.getAxis() == Direction.Axis.X ? (direction == Direction.EAST ?
                    2 : 0) : 1;
            int yStart = direction == Direction.UP ? 0 : (direction == Direction.DOWN
                    ? -2 : -1);
            int yEnd = direction == Direction.UP ? 2 : (direction == Direction.DOWN
                    ? 0 : 1);
            int zStart = direction.getAxis() == Direction.Axis.Z ? (direction == Direction.SOUTH
                    ? 0 : -2) : -1;
            int zEnd = direction.getAxis() == Direction.Axis.Z ? (direction == Direction.SOUTH ?
                    2 : 0) : 1;

            for (int x = xStart; x <= xEnd; x++) {
                for (int y = yStart; y <= yEnd; y++) {
                    for (int z = zStart; z <= zEnd; z++) {
                        BlockPos targetPos = pos.offset(x, y, z);
                        BlockState state = level.getBlockState(targetPos);
                        // プレイヤーによってブロックを破壊
                        state.getBlock().playerDestroy(player.level(),
                                player, targetPos, state, null, mainHandItem);
                        // ブロックを破壊
                        level.destroyBlock(targetPos, false);
                        // 経験値をドロップ
                        if (level instanceof ServerLevel serverLevel) {
                            state.getBlock().popExperience(serverLevel, targetPos,
                                    event.getExpToDrop());
                        }
                    }
                }
            }
        }
    }
}
