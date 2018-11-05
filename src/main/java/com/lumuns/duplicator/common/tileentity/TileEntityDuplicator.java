package com.lumuns.duplicator.common.tileentity;

import com.lumuns.duplicator.common.Duplicator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TileEntityDuplicator extends TileEntity implements IInventory, ISidedInventory {
    public void activated(World world, BlockPos pos, EntityPlayer player) {
        player.openGui(Duplicator.instance, 3, world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[] {0, 1};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public int getSizeInventory() { return 2; }

    @Override
    public boolean isEmpty() { return false; }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if(stack.isEmpty())
            return;

        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() { return 1; }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.add( new Vec3i(0.5D, 0.5D, 0.5D) )) <= 60.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) { }

    @Override
    public void closeInventory(EntityPlayer player) { }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) { return 0; }

    @Override
    public void setField(int id, int value) { }

    @Override
    public int getFieldCount() { return 0; }

    @Override
    public void clear() { }

    @Override
    public String getName() { return "duplicator"; }

    @Override
    public boolean hasCustomName() { return false; }
}
