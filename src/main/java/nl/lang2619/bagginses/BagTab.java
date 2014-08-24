package nl.lang2619.bagginses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import nl.lang2619.bagginses.items.ModItems;

/**
 * Created by Tim on 7/29/2014.
 */
public class BagTab extends CreativeTabs{

    public BagTab(int id,String label) {
        super(id,label);
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.red;
    }

    public String getTranslatedTabLabel(){
        return "Bagginses";
    }
}
