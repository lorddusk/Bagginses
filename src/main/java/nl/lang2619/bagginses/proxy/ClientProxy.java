package nl.lang2619.bagginses.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.lang2619.bagginses.helpers.KeybindHandler;
import nl.lang2619.bagginses.items.ModItems;

/**
 * Created by Tim on 8/24/2014.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModItems.registerModels();

        KeybindHandler.registerKeybinds();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerEvents();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    private void registerEvents() {
        //KeyBinds
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
    }
}
