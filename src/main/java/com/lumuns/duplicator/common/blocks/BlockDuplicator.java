package com.lumuns.duplicator.common.blocks;

import com.lumuns.duplicator.common.tileentity.TileEntityDuplicator;
import com.lumuns.duplicator.common.utils.Ref;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDuplicator extends BlockContainer {

    BlockDuplicator() {
        super(Material.ROCK);

        this.setCreativeTab(CreativeTabs.INVENTORY);
        this.setResistance(0.1f);
        this.setHardness(0.1f);
        this.setRegistryName(Ref.MODID, "duplicator");
        this.setUnlocalizedName(Ref.MODID +"_"+"duplicator");
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
                ((TileEntityDuplicator) worldIn.getTileEntity(pos)).activated(worldIn, pos, playerIn);
                return true;
            }
            return false;
        }
        else
            return true;

    }
}
