package nl.lang2619.bagginses.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.config.ConfigHandler;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.event.*;
import nl.lang2619.bagginses.gameanalytics.minecraft.MCSimpleAnalytics;
import nl.lang2619.bagginses.helpers.Messages.BagDescMessage;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.references.Achievements;
import nl.lang2619.bagginses.references.BlockList;
import nl.lang2619.bagginses.references.Defaults;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by Tim on 8/24/2014.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        //Stops "Failed to send analytics event data"
        System.setErr(new PrintStream(new OutputStream() {
            public void write(int b) {
            }
        }));

        Bagginses.path = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Defaults.MODID.toLowerCase() + File.separator;
        ConfigHandler.init(Bagginses.path);

        Bagginses.analytics = new MCSimpleAnalytics(Defaults.VERSION, Defaults.GAMEKEY, Defaults.SECRETKEY);
        Bagginses.analytics.eventDesign(Defaults.VERSION + ":ModStarted:" + Bagginses.analytics.userPrefix(), Bagginses.analytics.userPrefix());

        ModItems.init();

        if(event.getSide() == Side.CLIENT){
            ModItems.registerModels();
        }
    }

    public void init(FMLInitializationEvent event) {
        registerEvents();
        NetworkRegistry.INSTANCE.registerGuiHandler(Bagginses.instance, Bagginses.guiHandler);

        Bagginses.INSTANCE.registerMessage(BagDescMessage.MyMessageHandler.class, BagDescMessage.class, 0, Side.CLIENT);

        Achievements.init();
    }

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

    private void registerEvents(){
        //Closing container on item toss
        MinecraftForge.EVENT_BUS.register(new ItemEvent());
        //Showing allowed/not allowed in bag
        MinecraftForge.EVENT_BUS.register(new TooltipEventHandler());
        //For achievement firing
        MinecraftForge.EVENT_BUS.register(new AchievementEventHandler());
        //On player death and respawn
        MinecraftForge.EVENT_BUS.register(new SoulBoundEventHandler());
        //For the pickup events
        MinecraftForge.EVENT_BUS.register(new ItemDropEvent());
        //Player join and leave serbers
        MinecraftForge.EVENT_BUS.register(new PlayerServerEventHandler());
    }

}
