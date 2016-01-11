package nl.lang2619.bagginses.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

/**
 * Created by Tim on 4/12/2015.
 */
public class NBTHelper {

    public static void clearStatefulNBTTags(ItemStack itemStack)
    {
        if (NBTHelper.hasTag(itemStack, Names.NBT.BAG_OPEN))
        {
            NBTHelper.removeTag(itemStack, Names.NBT.BAG_OPEN);
        }
    }

    public static boolean hasTag(ItemStack itemStack, String keyName) {
        return itemStack != null && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(keyName);
    }

    public static void removeTag(ItemStack itemStack, String keyName) {
        if (itemStack.getTagCompound() != null) {
            itemStack.getTagCompound().removeTag(keyName);
        }
    }

    public static boolean hasUUID(ItemStack itemStack)
    {
        return hasTag(itemStack, Names.NBT.UUID_MOST_SIG) && hasTag(itemStack, Names.NBT.UUID_LEAST_SIG);
    }

    public static void setUUID(ItemStack itemStack)
    {
        initNBTTagCompound(itemStack);

        // Set a UUID on the Alchemical Bag, if one doesn't exist already
        if (!hasTag(itemStack, Names.NBT.UUID_MOST_SIG) && !hasTag(itemStack, Names.NBT.UUID_LEAST_SIG))
        {
            UUID itemUUID = UUID.randomUUID();
            setLong(itemStack, Names.NBT.UUID_MOST_SIG, itemUUID.getMostSignificantBits());
            setLong(itemStack, Names.NBT.UUID_LEAST_SIG, itemUUID.getLeastSignificantBits());
        }
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setLong(keyName, keyValue);
    }

    private static void initNBTTagCompound(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setBoolean(keyName, keyValue);
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setString(keyName, keyValue);
    }
}
