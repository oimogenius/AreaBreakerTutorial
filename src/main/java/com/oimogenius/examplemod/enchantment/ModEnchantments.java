package com.oimogenius.examplemod.enchantment;

import com.oimogenius.examplemod.ExampleMod;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ExampleMod.MODID);
    public static final RegistryObject<Enchantment> AREA_BREAKER =
            ENCHANTMENTS.register("area_breaker", () -> new AreaBreakerEnchantment());

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
