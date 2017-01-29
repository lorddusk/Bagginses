package nl.lang2619.bagginses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Alex on 27/01/2017.
 */
public class BagTab extends CreativeTabs {

    public BagTab(int id,String label) {
        super(id,label);
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.foid;
    }

    public String getTranslatedTabLabel(){
        return ModInfo.NAME;
    }
}
