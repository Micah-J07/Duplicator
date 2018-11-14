package com.lumuns.duplicator.common.blocks;

import com.lumuns.duplicator.common.Duplicator;
import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDuplicator extends BlockContainer {

    public BlockDuplicator() {
        super(Material.ROCK);

        this.setCreativeTab(CreativeTabs.MISC);
        this.setResistance(0.1f);
        this.setHardness(0.1f);
        this.setRegistryName(Ref.MODID, "duplicator");
        this.setUnlocalizedName(Ref.MODID+".duplicator");
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDuplicator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            if(worldIn.getTileEntity(pos) instanceof TileEntityDuplicator) {
                playerIn.openGui(Duplicator.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
            return false;
        }
        else
            return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityDuplicator tileentity = (TileEntityDuplicator)worldIn.getTileEntity(pos);
        if( tileentity != null)
            InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return true;
    }
}
