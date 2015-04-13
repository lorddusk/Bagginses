package nl.lang2619.bagginses.helpers;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Tim on 4/12/2015.
 */
public interface INBTTaggable {

    void readFromNBT(NBTTagCompound nbtTagCompound);

    void writeToNBT(NBTTagCompound nbtTagCompound);

    String getTagLabel();
}
