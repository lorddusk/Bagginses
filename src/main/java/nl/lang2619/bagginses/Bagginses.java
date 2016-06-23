package nl.lang2619.bagginses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import nl.lang2619.bagginses.config.ConfigHandler;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.event.*;
import nl.lang2619.bagginses.helpers.Messages.BagDescMessage;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.proxy.CommonProxy;
import nl.lang2619.bagginses.references.Achievements;
import nl.lang2619.bagginses.references.BlockList;
import nl.lang2619.bagginses.references.Defaults;
import scala.tools.nsc.doc.model.Def;

import java.io.File;

/**
 * Created by Tim on 7/29/2014.
 */
@Mod(modid = Defaults.MODID, version = "2.4.2d", name = Defaults.NAME)
public class Bagginses {
    @Mod.Instance(Defaults.MODID)
    public static Bagginses instance;

    @SidedProxy(clientSide = Defaults.CLIENTPROXY, serverSide = Defaults.COMMONPROXY)
    public static CommonProxy proxy;

    public static GuiHandler guiHandler = new GuiHandler();

    public static CreativeTabs BagTab = new BagTab(CreativeTabs.getNextID(), Defaults.NAME);

    public static String path;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Defaults.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
