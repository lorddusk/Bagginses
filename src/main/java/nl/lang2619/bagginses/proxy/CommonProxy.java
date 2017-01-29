package nl.lang2619.bagginses.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.lang2619.bagginses.ModItems;

/**
 * Created by Alex on 27/01/2017.
 */
public class CommonProxy {

  public void preInit(FMLPreInitializationEvent event) {
      ModItems.init();
  }

  public void init(FMLInitializationEvent event) {
  }

  public void postInit(FMLPostInitializationEvent event) {

  }

}
