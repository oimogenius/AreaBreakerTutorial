package com.oimogenius.examplemod.enchantment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AreaBreakerEnchantment extends Enchantment {
    protected AreaBreakerEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.DIGGER,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        // ツルハシにのみエンチャントできるようにする
        return super.canEnchant(pStack) && pStack.is(ItemTags.PICKAXES);
    }
}
