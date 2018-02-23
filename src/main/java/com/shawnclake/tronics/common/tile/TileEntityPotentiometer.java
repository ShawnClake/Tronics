package com.shawnclake.tronics.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPotentiometer extends TileEntity {

    private int ohms = 0;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Ohms", this.ohms);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.ohms = compound.getInteger("Ohms");
    }

    public void cycle()
    {
        ohms++;
        if(ohms > 15)
            ohms = 0;
    }

    public int getOhms() {
        return ohms;
    }

    public void setOhms(int ohms) {
        this.ohms = ohms;
    }
}
