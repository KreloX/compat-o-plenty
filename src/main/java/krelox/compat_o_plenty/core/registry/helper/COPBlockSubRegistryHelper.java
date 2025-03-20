package krelox.compat_o_plenty.core.registry.helper;

import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.integrations.farmersdelight.COPFarmersDelight;
import krelox.compat_o_plenty.integrations.quark.COPQuark;
import krelox.compat_o_plenty.integrations.twigs.COPTwigs;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class COPBlockSubRegistryHelper extends BlockSubRegistryHelper {
    public COPBlockSubRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public RegistryObject<Block> createCabinetBlock(String materialName, int burnTime) {
        return createFuelBlock(materialName + "_cabinet", areModsLoaded(CompatOPlenty.FARMERS_DELIGHT_ID)
                ? COPFarmersDelight.cabinet(Properties.copy(Blocks.BARREL))
                : () -> new Block(Properties.copy(Blocks.BARREL)), burnTime);
    }

    public RegistryObject<Block> createTableBlock(String materialName, Properties properties) {
        return createBlock(materialName + "_table", areModsLoaded(CompatOPlenty.TWIGS_ID)
                ? COPTwigs.table(properties)
                : () -> new Block(properties));
    }

    public RegistryObject<Block> createHollowLogBlock(String materialName, Supplier<Block> log, Properties properties) {
        return createFuelBlock("hollow_" + materialName + "_log", areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.hollowLog(log, true)
                : () -> new Block(properties), properties.ignitedByLava ? 300 : -1);
    }

    public RegistryObject<Block> createVerticalSlabBlock(String name, Supplier<Block> slab, Properties properties) {
        return createBlock(name, areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.verticalSlab(slab, properties)
                : () -> new Block(properties));
    }

    public RegistryObject<Block> createWoodVerticalSlabBlock(String materialName, Properties properties) {
        return createFuelBlock(materialName + "_vertical_slab", areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.verticalSlab(CompatOPlenty.bopBlock(materialName + "_slab"), properties)
                : () -> new Block(properties), properties.ignitedByLava ? 150 : -1);
    }

    public RegistryObject<Block> createWoodPostBlock(String prefix, String materialName, Block fence, SoundType sound, int burnTime) {
        return createFuelBlock(prefix + materialName + "_post", areModsLoaded(CompatOPlenty.QUARK_ID)
                ? COPQuark.woodPost(fence, sound)
                : () -> new Block(Properties.copy(fence)), burnTime);
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

    public RegistryObject<Block> createLeafCarpetBlock(String materialName) {
        return createLeafCarpetBlock(materialName + "_leaf_carpet", CompatOPlenty.bopBlock(materialName + "_leaves"));
    }

    @Override
    public RegistryObject<BlueprintChestBlock> createChestBlock(String materialName, Properties properties) {
        String name = materialName + "_chest";
        String modId = parent.getModId();
        String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, materialName, false);
        var block = deferredRegister.register(name, () -> new BlueprintChestBlock(chestMaterialsName, properties));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(false), properties.ignitedByLava ? 300 : -1));
        return block;
    }

    @Override
    public RegistryObject<BlueprintTrappedChestBlock> createTrappedChestBlockNamed(String materialName, Properties properties) {
        String name = "trapped_" + materialName + "_chest";
        String modId = parent.getModId();
        BlueprintChestMaterials.registerMaterials(modId, materialName, true);
        var block = deferredRegister.register(name, () -> new BlueprintTrappedChestBlock(modId + ":" + materialName + "_trapped", properties));
        itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true), properties.ignitedByLava ? 300 : -1));
        return block;
    }

    @OnlyIn(Dist.CLIENT)
    private static LazyBEWLR chestBEWLR(boolean trapped) {
        return trapped
                ? new LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState())))
                : new LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState())));
    }
}
