package nl.lang2619.bagginses;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import nl.lang2619.bagginses.config.ConfigHandler;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.event.PickUpEventHandler;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.proxy.CommonProxy;
import nl.lang2619.bagginses.references.BlockList;
import nl.lang2619.bagginses.references.Defaults;
import nl.lang2619.bagginses.references.Log;

import java.io.File;

/**
 * Created by Tim on 7/29/2014.
 */
@Mod(modid = Defaults.MODID, version = Defaults.VERSION, name = Defaults.NAME)
public class Bagginses {
    @Mod.Instance(Defaults.MODID)
    public static Bagginses instance;

    @SidedProxy(clientSide = Defaults.CLIENTPROXY, serverSide = Defaults.COMMONPROXY)
    public static CommonProxy proxy;

    public static GuiHandler guiHandler = new GuiHandler();

    public static CreativeTabs BagTab = new BagTab(CreativeTabs.getNextID(), "Bagginses");

    public static String path;

    public static Configuration configuration;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        path = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Defaults.MODID.toLowerCase() + File.separator;
        ConfigHandler.init(path);

        ModItems.init();

        if(event.getSide() == Side.CLIENT){
            ModItems.registerModels();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        instance = this;
        proxy.registerEvents();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new PickUpEventHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        BlockList.addFromString(ModConfig.black, "black");
        BlockList.addFromString(ModConfig.red, "red");
        BlockList.addFromString(ModConfig.brown, "brown");
        BlockList.addFromString(ModConfig.blue, "blue");
        BlockList.addFromString(ModConfig.cyan, "cyan");
        BlockList.addFromString(ModConfig.gray, "gray");
        BlockList.addFromString(ModConfig.green, "green");
        BlockList.addFromString(ModConfig.lightBlue, "lightBlue");
        BlockList.addFromString(ModConfig.lime, "lime");
        BlockList.addFromString(ModConfig.magenta, "magenta");
        BlockList.addFromString(ModConfig.orange, "orange");
        BlockList.addFromString(ModConfig.pink, "pink");
        BlockList.addFromString(ModConfig.purple, "purple");
        BlockList.addFromString(ModConfig.silver, "silver");
        BlockList.addFromString(ModConfig.white, "white");
        BlockList.addFromString(ModConfig.yellow, "yellow");
    }

}
