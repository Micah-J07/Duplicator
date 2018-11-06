package com.lumuns.duplicator.client.gui;

import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DuplicatorGui extends GuiContainer {
    private TileEntityDuplicator teDuplicator;
    private DuplicatorContainer container;

    private static ResourceLocation background = new ResourceLocation(Ref.MODID, "textures/gui/duplicator_ui.png");

    public DuplicatorGui(TileEntityDuplicator teDuplicator, DuplicatorContainer inventorySlotsIn) {
        super(inventorySlotsIn);

        this.teDuplicator = teDuplicator;
        this.container = inventorySlotsIn;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
