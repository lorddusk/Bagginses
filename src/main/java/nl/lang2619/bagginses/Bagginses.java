package nl.lang2619.bagginses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.lang2619.bagginses.proxy.CommonProxy;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alex on 27/01/2017.
 */
@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, name = ModInfo.NAME)
public class Bagginses {

  @Mod.Instance(ModInfo.MODID)
  public static Bagginses instance;

  @SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
  public static CommonProxy proxy;

  public BagTab creativeTab = new BagTab(CreativeTabs.getNextID(), ModInfo.NAME);

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
      try {
          FileWriter writer = new FileWriter("text.txt");

          for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
              writer.write("item.backpack_" + dyeColor.getName() + "_t1.name=" + dyeColor.getName().substring(0, 1).toUpperCase() + dyeColor.getName().substring(1) + " Bag, Tier 1");
              writer.write("\n");
              writer.write("item.backpack_" + dyeColor.getName() + "_t2.name=" + dyeColor.getName().substring(0, 1).toUpperCase() + dyeColor.getName().substring(1) + " Bag, Tier 2");
              writer.write("\n");
              writer.write("item.backpack_" + dyeColor.getName() + "_t3.name=" + dyeColor.getName().substring(0, 1).toUpperCase() + dyeColor.getName().substring(1) + " Bag, Tier 3");
              writer.write("\n\n");
          }

          writer.flush();
          writer.close();
      } catch (IOException e) {
          e.printStackTrace();
      }

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
