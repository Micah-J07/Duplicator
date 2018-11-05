package com.lumuns.duplicator.common;

import com.lumuns.duplicator.common.proxy.IProxy;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid= Ref.MODID, name = Ref.NAME, version = Ref.VERSION)
public class Duplicator {

    public static Minecraft mc = Minecraft.getMinecraft();

    @Mod.Instance(Ref.MODID)
    public static Duplicator instance;

    public static Logger logger;

    @SidedProxy(clientSide="com.lumuns.duplicator.proxy.ClientProxy", serverSide = "com.lumuns.duplicator.proxy.ServerProxy")
    private static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit( event );
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init( event );
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit( event );
    }

}
