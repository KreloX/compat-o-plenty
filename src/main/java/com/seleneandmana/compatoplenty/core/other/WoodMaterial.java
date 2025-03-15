package com.seleneandmana.compatoplenty.core.other;

import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum WoodMaterial {
    JACARANDA("jacaranda", WoodSetProperties.builder(MapColor.TERRACOTTA_PINK).build()),
    FIR("fir", WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build()),
    REDWOOD("redwood", WoodSetProperties.builder(MapColor.TERRACOTTA_ORANGE).build()),
    MAHOGANY("mahogany", WoodSetProperties.builder(MapColor.TERRACOTTA_PINK).build()),
    WILLOW("willow", WoodSetProperties.builder(MapColor.TERRACOTTA_LIGHT_GREEN).build()),
    MAGIC("magic", WoodSetProperties.builder(MapColor.COLOR_BLUE).build()),
    DEAD("dead", WoodSetProperties.builder(MapColor.STONE).build()),
    UMBRAN("umbran", WoodSetProperties.builder(MapColor.TERRACOTTA_BLUE).build()),
    PALM("palm", WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).build()),
    HELLBARK("hellbark", WoodSetProperties.builder(MapColor.TERRACOTTA_GRAY).fireproof().build());

    public static final WoodMaterial[] WOOD_MATERIALS = values();

    private final String name;
    private final WoodSetProperties properties;
    private final Supplier<Integer> burnTime;

    WoodMaterial(String name, WoodSetProperties properties) {
        this.name = name;
        this.properties = properties;
        this.burnTime = () -> {
            var item = ForgeRegistries.ITEMS.getValue(CompatOPlenty.bopLoc(name + "_planks"));
            return item.getBurnTime(item.getDefaultInstance(), RecipeType.SMELTING);
        };
    }

    public String getName() {
        return name;
    }

    public WoodSetProperties getProperties() {
        return properties;
    }

    public int getBurnTime() {
        return burnTime.get();
    }
}
