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
import net.minecraftforge.common.MinecraftForge;
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
import org.lwjgl.input.Keyboard;

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
        if (type == BagTypes.TIER1)
            list.add("Tier 1");
        if (type == BagTypes.TIER2)
            list.add("Tier 2");
        if (type == BagTypes.VOID)
            list.add("Void Bag");
        if (type == BagTypes.ENDER)
            list.add("Ender Chest Bag");
        if (isSoulBound(stack)) {
            list.add(ChatFormatting.LIGHT_PURPLE + "Soulbound");
        }
        try {
            if (ModItems.bagInfo[ModItems.getWoolForColor(color)] != null
                    && !ModItems.bagInfo[ModItems.getWoolForColor(color)].isEmpty()) {
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                    list.add(ChatFormatting.AQUA + ModItems.bagInfo[ModItems.getWoolForColor(color)]);
                }
                else {
                    list.add(ChatFormatting.GRAY + "Hold shift to see custom name");
                }
            }
        }
        catch (NullPointerException e) {
            //Ignore
        }
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
