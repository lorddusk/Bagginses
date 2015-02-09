package nl.lang2619.bagginses.items.bags.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Tim on 8/24/2014.
 */
public class SlotNoPickup extends Slot {
    public SlotNoPickup(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory,slotIndex,x,y);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player){
        return false;
    }
}
