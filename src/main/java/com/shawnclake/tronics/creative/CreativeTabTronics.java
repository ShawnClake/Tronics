package com.shawnclake.tronics.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabTronics extends CreativeTabs {

    public CreativeTabTronics() {
        super("Tronics");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.CARROT_ON_A_STICK);
    }
}
