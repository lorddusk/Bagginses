package nl.lang2619.bagginses.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.lang2619.bagginses.ModInfo;
import nl.lang2619.bagginses.items.Bag;

/**
 * Created by Alex on 27/01/2017.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    @Override
    public void registerItemRenderer(Bag bag) {
        ModelLoader.setCustomModelResourceLocation(bag, 0, new ModelResourceLocation(ModInfo.MODID + ":backpack_" + bag.getColor(), "inventory"));
    }
}
