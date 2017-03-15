package nl.lang2619.bagginses.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.ModItems;
import nl.lang2619.bagginses.helpers.BagType;
import nl.lang2619.bagginses.helpers.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alex on 27/01/2017.
 */
public class Bag extends BagginsesItem {

    String colour;
    BagType type;

    public Bag(String colour, BagType type) {
        super();
        maxStackSize = 1;
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
    public void registerItemModel() {
        Bagginses.proxy.registerItemRenderer(this);
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

        String msg = "";
        if (ModItems.bags.get(colour).getServerDesc() != null
                && !ModItems.bags.get(colour).getServerDesc().equals("")) {
            msg = ModItems.bags.get(colour).getServerDesc();
        } else if (ModItems.bags.get(colour).getDesc() != null
                && !ModItems.bags.get(colour).getDesc().equals("")) {
            msg = ModItems.bags.get(colour).getDesc();
        }

        Arrays.stream(msg.split("\n"))
                .limit(3)
                .filter(Objects::nonNull)
                .filter(part -> !("").equals(part))
                .forEach(part -> list.add(part));

            /*if (!("").equals(msg)) {
                String[] parts = msg.split("\n");
                for (int i = 0; i < Math.min(3, parts.length); i++) {
                    if (parts[i] != null
                            && !("").equals(parts[i])) {
                        list.add(parts[i]);
                    }
                }
            }*/
    }

}
