package com.lumuns.duplicator.client.gui;

import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.lwjgl.Sys;

import javax.annotation.ParametersAreNonnullByDefault;

public class DuplicatorContainer extends Container {

    private TileEntityDuplicator duplicator;

    public DuplicatorContainer(InventoryPlayer player, TileEntityDuplicator tileEntity) {
        duplicator = tileEntity;

        IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 55, 33));
        addSlotToContainer(new DuplicatorSlot(player, 1, 105, 33));

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
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack currentStack = slot.getStack();
            stack = currentStack.copy();

            if (index == 0) {
                if (!this.mergeItemStack(currentStack, 0, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else {
                if (!this.mergeItemStack(currentStack, 0, 1, true))
                    return ItemStack.EMPTY;
            }

            if (currentStack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
        }

        return stack;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer playerIn) {
        return duplicator.isUsableByPlayer(playerIn);
    }
}
