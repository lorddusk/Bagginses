package nl.lang2619.bagginses.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.helpers.Util;

import java.util.List;

/**
 * Created by Alex on 27/01/2017.
 */
public class Bag extends Item {

    String colour;
    BagType type;

    public Bag(String colour, BagType type) {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.instance.creativeTab);
        this.colour = colour;
        this.type = type;

        this.setUnlocalizedName("backpack_" + colour + "_" +  type.getTierCode());
    }

    public String getColor() {
        return colour;
    }

    public BagType getType() {
        return type;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return Util.isSoulbound(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);

        //TODO modes
        /*if (BagMode.getMode(stack) != BagMode.DEFAULT) {
            list.add(ChatFormatting.AQUA + "Mode: " + BagMode.getMode(stack).getName());
        }*/

        if (Util.isSoulbound(stack)) {
            list.add(ChatFormatting.LIGHT_PURPLE + "Soulbound");
        }

        //TODO Descriptions
        /*try {
            String msg = "";
            if (ModItems.bags.get(color).getServerDesc() != null
                    && !ModItems.bags.get(color).getServerDesc().equals("")) {
                msg = ModItems.bags.get(color).getServerDesc();
            } else if (ModItems.bags.get(color).getDesc() != null
                    && !ModItems.bags.get(color).getDesc().equals("")) {
                msg = ModItems.bags.get(color).getDesc();
            }

            if (!("").equals(msg)) {
                String[] parts = msg.split("\n");
                for (int i = 0; i < Math.min(3, parts.length); i++) {
                    if (parts[i] != null
                            && !("").equals(parts[i])) {
                        list.add(parts[i]);
                    }
                }
            }
        } catch (NullPointerException e) {
            //Ignore
        }*/
    }

}
