package com.seleneandmana.compatoplenty.core.other;

import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class WoodMaterial {
    public static final WoodMaterial JACARANDA = new WoodMaterial("jacaranda", WoodSetProperties.builder(MapColor.TERRACOTTA_PINK).build());
    public static final WoodMaterial FIR = new WoodMaterial("fir", WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build());
    public static final WoodMaterial REDWOOD = new WoodMaterial("redwood", WoodSetProperties.builder(MapColor.TERRACOTTA_ORANGE).build());
    public static final WoodMaterial MAHOGANY = new WoodMaterial("mahogany", WoodSetProperties.builder(MapColor.TERRACOTTA_PINK).build());
    public static final WoodMaterial WILLOW = new WoodMaterial("willow", WoodSetProperties.builder(MapColor.TERRACOTTA_LIGHT_GREEN).build());
    public static final WoodMaterial MAGIC = new WoodMaterial("magic", WoodSetProperties.builder(MapColor.COLOR_BLUE).build());
    public static final WoodMaterial DEAD = new WoodMaterial("dead", WoodSetProperties.builder(MapColor.STONE).build());
    public static final WoodMaterial UMBRAN = new WoodMaterial("umbran", WoodSetProperties.builder(MapColor.TERRACOTTA_BLUE).build());
    public static final WoodMaterial PALM = new WoodMaterial("palm", WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).build());
    // TODO fireproof()?
    public static final WoodMaterial HELLBARK = new WoodMaterial("hellbark", WoodSetProperties.builder(MapColor.TERRACOTTA_GRAY).build());

    public static final List<WoodMaterial> WOOD_MATERIALS = List.of(JACARANDA, FIR, REDWOOD, MAHOGANY, WILLOW, MAGIC, DEAD, UMBRAN, PALM, HELLBARK);

    private final String name;
    private final WoodSetProperties properties;
    private final Supplier<Integer> burnTime;

    public WoodMaterial(String name, WoodSetProperties properties) {
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

    public Supplier<Block> getBlock(String suffix) {
        return () -> ForgeRegistries.BLOCKS.getValue(CompatOPlenty.bopLoc(name + suffix));
    }
}
