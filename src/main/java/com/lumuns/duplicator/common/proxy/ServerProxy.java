package com.lumuns.duplicator.common.proxy;

import com.lumuns.duplicator.common.blocks.LumunsBlocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {
    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        LumunsBlocks.preInt();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
