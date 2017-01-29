package nl.lang2619.bagginses.items;

import net.minecraft.item.Item;
import nl.lang2619.bagginses.Bagginses;

/**
 * Created by Alex on 29/01/2017.
 */
public class BagginsesItem extends Item {

    public BagginsesItem() {
        this.setCreativeTab(Bagginses.instance.creativeTab);
    }

    public void registerItemModel() {
        Bagginses.proxy.registerItemRenderer(this, 0);
    }
}
