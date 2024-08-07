package com.oimogenius.examplemod.datagen;

import com.oimogenius.examplemod.ExampleMod;
import com.oimogenius.examplemod.enchantment.ModEnchantments;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class JAJPLanguageProvider extends LanguageProvider {
    public JAJPLanguageProvider(PackOutput output) {
        super(output, ExampleMod.MODID, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        addEnchantment(ModEnchantments.AREA_BREAKER, "範囲破壊");

    }
}
