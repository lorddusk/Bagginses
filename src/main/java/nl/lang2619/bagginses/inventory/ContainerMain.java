package nl.lang2619.bagginses.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Tim on 8/24/2014.
 */
public abstract class ContainerMain extends Container {
    public void addSlot(Slot slot){
        addSlotToContainer(slot);
    }
}