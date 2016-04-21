package nl.lang2619.bagginses.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.items.bags.Bags;
import nl.lang2619.bagginses.items.bags.gui.BagGui;
import nl.lang2619.bagginses.references.BlockList;

/**
 * Created by alex_ on 21/04/2016.
 */
public class TooltipEvent {

    @SubscribeEvent
    public void tooltip(ItemTooltipEvent e) {
        if (Minecraft.getMinecraft().currentScreen instanceof BagGui) {
            Bags bag = (Bags) getStack(e.getEntityPlayer()).getItem();
            String color = bag.getColor();

            if (!isItemAllowed(e.getItemStack(), color)) {
                e.getToolTip().add(ChatFormatting.RED + "This item is not allowed in this bag");
            }
        }
    }

    static boolean isItemAllowed(ItemStack stack, String color) {
        if (ModConfig.whitelist) {
            //Whitelist
            if (BlockList.contains(stack.getItem(), stack.getItemDamage(), color)) {
                return true;
            }else{
                return false;
            }
        }else{
            //Blacklist
            if (BlockList.contains(stack.getItem(), stack.getItemDamage(), color)) {
                return false;
            }else{
                return true;
            }
        }
    }

    static ItemStack getStack(EntityPlayer p) {
        if (p.getHeldItemMainhand() != null
                && p.getHeldItemMainhand().getItem() instanceof Bags) {
            return p.getHeldItemMainhand();
        } else {
            return p.getHeldItemOffhand();
        }
    }

}
