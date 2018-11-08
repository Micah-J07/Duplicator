package com.lumuns.duplicator.client.gui;

import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class DuplicatorContainer extends Container {

    private TileEntityDuplicator tileEntityDuplicator;

    public DuplicatorContainer(InventoryPlayer player, TileEntityDuplicator tileEntity) {
        tileEntityDuplicator = tileEntity;

        this.addSlotToContainer(new Slot(tileEntity, 0, 55, 33));
        this.addSlotToContainer(new Slot(tileEntity, 1, 105, 33));

        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                this.addSlotToContainer(new Slot(player, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 58 + 84;
            this.addSlotToContainer(new Slot(player, row, x, y));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack currentStack = slot.getStack();
            stack = currentStack.copy();

            if (currentStack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (currentStack.getCount() == stack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, currentStack);
        }

        return stack;
    }


    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntityDuplicator.isUsableByPlayer(playerIn);
    }
}
