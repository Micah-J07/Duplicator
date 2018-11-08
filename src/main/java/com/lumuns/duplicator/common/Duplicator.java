package com.lumuns.duplicator.common;

import com.lumuns.duplicator.common.blocks.BlockDuplicator;
import com.lumuns.duplicator.common.network.GuiHandler;
import com.lumuns.duplicator.common.proxy.ClientProxy;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid= Ref.MODID, name = Ref.NAME, version = Ref.VERSION)
public class Duplicator {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static BlockDuplicator blockDuplicator;

    @Mod.Instance(Ref.MODID)
    public static Duplicator instance;

    public static Logger logger;

    @SidedProxy(clientSide="com.lumuns.duplicator.common.proxy.ClientProxy")
    private static ClientProxy clientProxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        clientProxy.preInit( event );


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Duplicator.instance, new GuiHandler());
        clientProxy.init( event );
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        clientProxy.postInit( event );
    }
}
