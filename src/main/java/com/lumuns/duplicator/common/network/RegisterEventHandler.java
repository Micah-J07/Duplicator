package com.lumuns.duplicator.common.network;

import com.lumuns.duplicator.common.blocks.BlockDuplicator;
import com.lumuns.duplicator.common.blocks.ModBlocks;
import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID)
public class RegisterEventHandler {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS);

        GameRegistry.registerTileEntity(TileEntityDuplicator.class, new ResourceLocation(Ref.MODID, "duplicator"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Block block: ModBlocks.BLOCKS)
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

}
