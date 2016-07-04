package nl.lang2619.bagginses.items.bags;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.helpers.ChatUtils;
import nl.lang2619.bagginses.helpers.ItemHelper;
import nl.lang2619.bagginses.helpers.NBTHelper;
import nl.lang2619.bagginses.helpers.Names;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.proxy.GuiInfo;
import nl.lang2619.bagginses.references.BagMode;
import nl.lang2619.bagginses.references.BagTypes;

import java.util.List;

/**
 * Created by Tim on 4/12/2015.
 */
public class Bag extends Item {
    String color;
    BagTypes type;
    BagMode mode;


    public Bag(String color, BagTypes type) {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.BagTab);
        this.color = color;
        this.type = type;
        this.mode = BagMode.DEFAULT;
    }

    public String getColor() {
        return color;
    }

    public BagTypes getType() {
        return type;
    }

    public BagMode getMode() {
        return mode;
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "backpack_" + color;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.getTagCompound() != null
                && stack.getTagCompound().hasKey("soulbound")
                && stack.getTagCompound().getBoolean("soulbound")) {
            return true;
        }
        return stack.isItemEnchanted();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                changeMode(itemStack, world, player, hand);
                return new ActionResult(EnumActionResult.SUCCESS, itemStack);
            }
            if (!ItemHelper.hasOwnerUUID(itemStack)) {
                ItemHelper.setOwner(itemStack, player);
            }
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, Names.NBT.BAG_OPEN, true);

            if (type == BagTypes.TIER1)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK, world, 0, 0, 0);
            if (type == BagTypes.TIER2)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_T2, world, 0, 0, 0);
            if (type == BagTypes.VOID)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_VOID, world, 0, 0, 0);
            if(type == BagTypes.ENDER) {
                InventoryEnderChest inventoryEnderChest = player.getInventoryEnderChest();
                player.displayGUIChest(inventoryEnderChest);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    }

    private void changeMode(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        if (type == BagTypes.VOID) {
            return;
        }
        if (!ModConfig.bagPickUp) {
            return;
        }
        mode = mode.next();
        ChatUtils.sendNoSpamMessages(14, new TextComponentString(ChatFormatting.AQUA + "Mode: " + mode.getName()));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);
        if (isSoulBound(stack)) {
            list.add(ChatFormatting.LIGHT_PURPLE + "Soulbound");
        }
        try {
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
        }
        catch (NullPointerException e) {
            //Ignore
        }

        /*if (type == BagTypes.TIER1)
            list.add("Tier 1");
        if (type == BagTypes.TIER2)
            list.add("Tier 2");
        if (type == BagTypes.VOID)
            list.add("Void Bag");
        if (type == BagTypes.ENDER)
            list.add("Ender Chest Bag");*/
    }

    private boolean isSoulBound(ItemStack stack) {
        try {
            if (stack != null
                    && stack.getTagCompound() != null
                    && stack.getTagCompound().hasKey("soulbound")
                    && stack.getTagCompound().getBoolean("soulbound")) {
                return true;
            }
        } catch (Exception e){

        }
        return false;
    }
}
