package com.lumuns.duplicator.common.blocks;

import com.lumuns.duplicator.common.items.ItemDuplicatorBlock;
import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Ref.MODID)
public class LumunsBlocks {
    public static BlockDuplicator duplicator;

    public static void preInt() {
        duplicator = new BlockDuplicator();

        ForgeRegistries.BLOCKS.register(duplicator);
        ForgeRegistries.ITEMS.register(new ItemDuplicatorBlock(duplicator).setRegistryName(Ref.MODID, "duplicator"));

        GameRegistry.registerTileEntity(TileEntityDuplicator.class, new ResourceLocation(Ref.MODID, "duplicator_tile"));
    }

    public static void registerRender() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(duplicator), 0, new ModelResourceLocation("duplicator:duplicator", "inventory"));
    }
}
