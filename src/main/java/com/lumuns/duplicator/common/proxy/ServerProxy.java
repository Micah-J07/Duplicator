package com.lumuns.duplicator.common.proxy;

import com.lumuns.duplicator.common.Duplicator;
import com.lumuns.duplicator.common.blocks.LumunsBlocks;
import com.lumuns.duplicator.common.network.GuiHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ServerProxy implements IProxy {
    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        LumunsBlocks.preInt();
        NetworkRegistry.INSTANCE.registerGuiHandler(Duplicator.instance, new GuiHandler());

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
