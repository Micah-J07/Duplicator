package com.lumuns.duplicator.common.proxy;

import com.lumuns.duplicator.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy {

    public void init(FMLInitializationEvent event) {
    }

    public void preInit(FMLPreInitializationEvent event) {
        for (Block block: ModBlocks.BLOCKS)
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(block),
                    0,
                    new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
