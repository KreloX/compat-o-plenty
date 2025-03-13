package com.seleneandmana.compatoplenty.core.registry.helper;

import com.ninni.twigs.block.TableBlock;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.integrations.farmersdelight.FallbackCabinetBlock;
import com.seleneandmana.compatoplenty.integrations.twigs.FallbackTableBlock;
import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.RegistryObject;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.block.LeafCarpetBlock;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.quark.content.building.block.WoodPostBlock;
import org.violetmoon.quark.content.building.module.HedgesModule;
import org.violetmoon.quark.content.building.module.LeafCarpetModule;
import org.violetmoon.quark.content.building.module.VerticalPlanksModule;
import org.violetmoon.quark.content.building.module.WoodenPostsModule;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class CompatBlockSubRegistryHelper extends BlockSubRegistryHelper {
    public CompatBlockSubRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public RegistryObject<Block> createVerticalSlabBlock(String name, Supplier<Block> slab, Properties properties) {
        return createBlock(name, () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? new VerticalSlabBlock(slab, properties)
                : new Block(properties));
    }

    public RegistryObject<Block> createWoodVerticalSlabBlock(WoodMaterial woodMaterial) {
        return createFuelBlock(woodMaterial.getName() + "_vertical_slab", () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? new VerticalSlabBlock(woodMaterial.getBlock("_slab"), woodMaterial.getProperties().planks())
                : new Block(woodMaterial.getProperties().planks()), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createWoodPostBlock(String prefix, WoodMaterial woodMaterial) {
        String name = prefix + woodMaterial.getName() + "_post";
        return createFuelBlock(name, () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? new WoodPostBlock(null, woodMaterial.getBlock("_fence").get(), prefix, woodMaterial.getProperties().sound())
                : new Block(woodMaterial.getProperties().post()), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createHedgeBlock(String name, Supplier<Block> fence, Supplier<Block> leaves, Properties properties, int burnTime) {
        return createFuelBlock(name, () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? new HedgeBlock(name, null, fence.get(), leaves.get())
                : new Block(properties), burnTime);
    }

    public RegistryObject<Block> createLeafCarpetBlock(String name, Supplier<Block> leaves, Properties properties) {
        return createBlock(name, () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? new LeafCarpetBlock(name, leaves.get(), null)
                : new Block(properties));
    }

    public RegistryObject<Block> createLeafCarpetBlock(WoodMaterial woodMaterial) {
        return createLeafCarpetBlock(woodMaterial.getName() + "_leaf_carpet",
                woodMaterial.getBlock("_leaves"), woodMaterial.getProperties().leafPile());
    }

    public RegistryObject<BlueprintChestBlock> createChestBlock(WoodMaterial woodMaterial) {
        String name = woodMaterial.getName() + "_chest";
        String modId = parent.getModId();
        String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, false);
        RegistryObject<BlueprintChestBlock> block = deferredRegister.register(name, () -> new BlueprintChestBlock(chestMaterialsName, woodMaterial.getProperties().chest()));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(false), woodMaterial.getBurnTime()));
        return block;
    }

    public RegistryObject<BlueprintTrappedChestBlock> createTrappedChestBlock(WoodMaterial woodMaterial) {
        String name = "trapped_" + woodMaterial.getName() + "_chest";
        String modId = parent.getModId();
        BlueprintChestMaterials.registerMaterials(modId, name, true);
        RegistryObject<BlueprintTrappedChestBlock> block = deferredRegister.register(name, () -> new BlueprintTrappedChestBlock(modId + ":" + woodMaterial.getName() + "_trapped", woodMaterial.getProperties().chest()));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true), woodMaterial.getBurnTime()));
        return block;
    }

    public RegistryObject<Block> createCabinetBlock(WoodMaterial woodMaterial) {
        return createFuelBlock(woodMaterial.getName() + "_cabinet", () -> areModsLoaded(CompatOPlenty.FARMERS_DELIGHT_ID)
                ? new CabinetBlock(Properties.copy(Blocks.BARREL))
                : new FallbackCabinetBlock(Properties.copy(Blocks.BARREL)), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createTableBlock(WoodMaterial woodMaterial) {
        return createBlock(woodMaterial.getName() + "_table", () -> areModsLoaded(CompatOPlenty.TWIGS_ID)
                ? new TableBlock(woodMaterial.getProperties().planks())
                : new FallbackTableBlock(woodMaterial.getProperties().planks()));
    }

    public RegistryObject<Block> createVerticalPlanksBlock(WoodMaterial woodMaterial) {
        String name = "vertical_" + woodMaterial.getName() + "_table";
        return createBlock(name, () -> areModsLoaded(CompatOPlenty.QUARK_ID)
                ? VerticalPlanksModule.add(name, woodMaterial.getBlock("_planks").get(), null)
                : new FallbackTableBlock(woodMaterial.getProperties().planks()));
    }

    private static BEWLRBlockItem.LazyBEWLR chestBEWLR(boolean trapped) {
        return trapped
                ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState())))
                : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState())));
    }
}
