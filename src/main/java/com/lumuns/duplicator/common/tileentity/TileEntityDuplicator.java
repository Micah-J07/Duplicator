package com.lumuns.duplicator.common.tileentity;

import com.lumuns.duplicator.common.Duplicator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityDuplicator extends TileEntity implements IInventory, ISidedInventory {

    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    private String blockName;

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if( side == EnumFacing.UP )
            return new int[] {0};

        return new int[] {0, 1};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        System.out.println(index);
        return index == 1;
    }

    @Override
    public int getSizeInventory() { return this.inventory.size(); }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack: this.inventory)
            if(!stack.isEmpty()) return false;

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if( index == 0 ) {
            if( this.inventory.get(0).getCount() - count == 0 && !this.inventory.get(1).isEmpty() )
                this.inventory.set(1, ItemStack.EMPTY);
        }

        if( index == 1 ) {
            ItemStack stack = ItemStackHelper.getAndSplit(this.inventory, index, count);

            ItemStack dupStack = this.inventory.get(0).copy();
            dupStack.setCount(dupStack.isStackable() ? dupStack.getMaxStackSize() : 1);
            this.inventory.set(1, dupStack);
            return stack;
        }

        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if(stack.isEmpty())
            return;

        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() { return 64; }

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
        return index == 0 && this.getItemStackHandler().getStackInSlot(0).isEmpty();
    }

    @Override
    public int getField(int id) { return 0; }

    @Override
    public void setField(int id, int value) { }

    @Override
    public int getFieldCount() { return 0; }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public String getName() { return this.hasCustomName() ? this.blockName : "duplicator"; }

    public void setName(String name) {
        this.blockName = name;
    }

    @Override
    public boolean hasCustomName() { return this.blockName != null && !this.blockName.isEmpty(); }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    private ItemStackHandler itemStackHandler = new ItemStackHandler(this.inventory.size()) {

        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            markDirty();
        }

        @Override
        public int getSlotLimit(int slot)
        {
            return slot == 1 ? 64 : 1;
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            System.out.println(stack.toString());
            return slot == 0 && this.getStackInSlot(0).isEmpty();
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if( slot == 0 ) {
                ItemStack dupStack = stack.copy();
                dupStack.setCount(dupStack.isStackable() ? dupStack.getMaxStackSize() : 1);

                this.setStackInSlot(1, dupStack);
            }

            return super.insertItem(slot, stack, simulate);
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
//            if( slot == 0 && !simulate)
//                return ItemStack.EMPTY;
            
            if( slot == 1 ) {
                ItemStack stack = this.getStackInSlot(slot);
                ItemStack taken = stack.copy();
                taken.setCount(64);

                super.extractItem(slot, amount, simulate);

                this.setStackInSlot(slot, taken);
                return stack;
            }

            return super.extractItem(slot, amount, simulate);
        }


    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);

        if(compound.hasKey("blockName"))
            this.setName(compound.getString("blockName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        ItemStackHelper.saveAllItems(compound, this.inventory);
        if( this.hasCustomName() )
            compound.setString("blockName", this.blockName);

        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
}
