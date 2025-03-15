package com.seleneandmana.compatoplenty.core.registry.helper;

import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.integrations.farmersdelight.COPCabinets;
import com.seleneandmana.compatoplenty.integrations.quark.COPQuark;
import com.seleneandmana.compatoplenty.integrations.twigs.COPTables;
import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem.LazyBEWLR;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class COPBlockSubRegistryHelper extends BlockSubRegistryHelper {
    public COPBlockSubRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public RegistryObject<Block> createCabinetBlock(WoodMaterial woodMaterial) {
        return createFuelBlock(woodMaterial.getName() + "_cabinet", areModsLoaded(CompatOPlenty.FARMERS_DELIGHT_ID)
                ? COPCabinets.cabinet(Properties.copy(Blocks.BARREL))
                : () -> new Block(Properties.copy(Blocks.BARREL)), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createTableBlock(WoodMaterial woodMaterial) {
        return createBlock(woodMaterial.getName() + "_table", areModsLoaded(CompatOPlenty.TWIGS_ID)
                ? COPTables.table(woodMaterial.getProperties().planks())
                : () -> new Block(woodMaterial.getProperties().planks()));
    }

    public RegistryObject<Block> createVerticalSlabBlock(String name, Supplier<Block> slab, Properties properties) {
        return createBlock(name, areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.verticalSlab(slab, properties)
                : () -> new Block(properties));
    }

    public RegistryObject<Block> createWoodVerticalSlabBlock(WoodMaterial woodMaterial) {
        return createFuelBlock(woodMaterial.getName() + "_vertical_slab", areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.verticalSlab(CompatOPlenty.bopBlock(woodMaterial.getName() + "_slab"), woodMaterial.getProperties().planks())
                : () -> new Block(woodMaterial.getProperties().planks()), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createWoodPostBlock(String prefix, WoodMaterial woodMaterial) {
        return createFuelBlock(prefix + woodMaterial.getName() + "_post", areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.woodPost(CompatOPlenty.bopBlock(woodMaterial.getName() + "_fence").get(), woodMaterial.getProperties().sound())
                : () -> new Block(woodMaterial.getProperties().post()), woodMaterial.getBurnTime());
    }

    public RegistryObject<Block> createHedgeBlock(String name, Supplier<Block> fence, Supplier<Block> leaves, int burnTime) {
        return createFuelBlock(name, areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.hedge(fence, leaves)
                : () -> new Block(Properties.copy(Blocks.OAK_FENCE)), burnTime);
    }

    public RegistryObject<Block> createLeafCarpetBlock(String name, Supplier<Block> leaves) {
        return createBlock(name, areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.leafCarpet(leaves)
                : () -> new Block(Properties.copy(Blocks.OAK_LEAVES)));
    }

    public RegistryObject<Block> createLeafCarpetBlock(WoodMaterial woodMaterial) {
        return createLeafCarpetBlock(woodMaterial.getName() + "_leaf_carpet",
                CompatOPlenty.bopBlock(woodMaterial.getName() + "_leaves"));
    }

    public RegistryObject<BlueprintChestBlock> createChestBlock(WoodMaterial woodMaterial) {
        String name = woodMaterial.getName() + "_chest";
        String modId = parent.getModId();
        String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, woodMaterial.getName(), false);
        var block = deferredRegister.register(name, () -> new BlueprintChestBlock(chestMaterialsName, woodMaterial.getProperties().chest()));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(false), woodMaterial.getBurnTime()));
        return block;
    }

    public RegistryObject<BlueprintTrappedChestBlock> createTrappedChestBlock(WoodMaterial woodMaterial) {
        String name = "trapped_" + woodMaterial.getName() + "_chest";
        String modId = parent.getModId();
        BlueprintChestMaterials.registerMaterials(modId, woodMaterial.getName(), true);
        var block = deferredRegister.register(name, () -> new BlueprintTrappedChestBlock(modId + ":" + woodMaterial.getName() + "_trapped", woodMaterial.getProperties().chest()));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true), woodMaterial.getBurnTime()));
        return block;
    }

    @OnlyIn(Dist.CLIENT)
    private static LazyBEWLR chestBEWLR(boolean trapped) {
        return trapped
                ? new LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState())))
                : new LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState())));
    }
}
