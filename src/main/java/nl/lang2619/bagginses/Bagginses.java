package nl.lang2619.bagginses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.lang2619.bagginses.proxy.CommonProxy;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alex on 27/01/2017.
 */
@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, name = ModInfo.NAME)
public class Bagginses {

  @Mod.Instance(ModInfo.MODID)
  public static Bagginses instance;
  public static Logger logger;

  @SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
  public static CommonProxy proxy;

  public BagTab creativeTab = new BagTab(CreativeTabs.getNextID(), ModInfo.NAME);

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
      logger = event.getModLog();
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
