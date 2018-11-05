package com.lumuns.duplicator.common.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    public void init(FMLInitializationEvent event);
    public void preInit(FMLPreInitializationEvent event);
    public void postInit(FMLPostInitializationEvent event);
}
