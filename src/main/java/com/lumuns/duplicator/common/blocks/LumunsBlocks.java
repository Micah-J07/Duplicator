package com.lumuns.duplicator.common.blocks;

import com.lumuns.duplicator.common.items.ItemDuplicatorBlock;
import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
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
}
