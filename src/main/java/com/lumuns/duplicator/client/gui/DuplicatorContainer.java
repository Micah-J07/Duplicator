package com.lumuns.duplicator.client.gui;

import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import javax.annotation.ParametersAreNonnullByDefault;

public class DuplicatorContainer extends Container {

    private TileEntityDuplicator duplicator;

    public DuplicatorContainer(InventoryPlayer player, TileEntityDuplicator tileEntity) {
        duplicator = tileEntity;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer playerIn) {
        return duplicator.isUsableByPlayer(playerIn);
    }
}
