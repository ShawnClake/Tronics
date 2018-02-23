package com.shawnclake.tronics.common.blocks;

import com.shawnclake.tronics.Tronics;
import com.shawnclake.tronics.common.tile.TileEntityPotentiometer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Objects;

public class BlockPotentiometer extends BlockHorizontal {

    protected static final AxisAlignedBB REDSTONE_DIODE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    //public static final PropertyInteger POWERED = PropertyInteger.create("powered", 0, 15);
    //public final int inputStrength = 0;
    //public final int ohms = 1;

    public BlockPotentiometer() {
        super(Material.CIRCUITS);
        //setUnlocalizedName("1kResistor");
        setHardness(0.8f);
        setSoundType(SoundType.WOOD);
        setCreativeTab(Tronics.creativeTab);

        setRegistryName(Tronics.MODID, "1kresistor");
        final ResourceLocation registryName = Objects.requireNonNull(getRegistryName());
        setUnlocalizedName(registryName.toString());
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return REDSTONE_DIODE_AABB;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityPotentiometer te = (TileEntityPotentiometer)worldIn.getTileEntity(pos);
            te.cycle();
            worldIn.setBlockState(pos, state, 3);
            //float f = ((Boolean)state.getValue(POWERED)).booleanValue() ? 0.6F : 0.5F;
            //worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            worldIn.notifyNeighborsOfStateChange(pos, this, true);
            return true;
        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        //if (((Boolean)state.getValue(POWERED)).booleanValue())
        //{
            worldIn.notifyNeighborsOfStateChange(pos, this, false);
            //worldIn.notifyNeighborsOfStateChange(pos, this, false);
        //}

        super.breakBlock(worldIn, pos, state);
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        //return ((Boolean)blockState.getValue(POWERED)).booleanValue() ? 15 : 0;

        if(blockState.getValue(FACING) == side)
        {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(blockState.getValue(FACING)));
            Block block = iblockstate.getBlock();

            TileEntityPotentiometer te = (TileEntityPotentiometer)blockAccess.getTileEntity(pos);

            if(block == Blocks.REDSTONE_WIRE) {
                int strength = ((Integer) iblockstate.getValue(BlockRedstoneWire.POWER));

                if(strength < te.getOhms())
                    return 0;

                return strength - te.getOhms();

                /*if(strength < blockState.getValue(POWERED))
                    return 0;

                return strength - blockState.getValue(POWERED);*/

                /*if (strength < this.ohms)
                    return 0;

                return strength - this.ohms;*/
            }
        }

        return 0;

    }

    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return this.getWeakPower(blockState, blockAccess, pos, side);
        /*if (!((Boolean)blockState.getValue(POWERED)).booleanValue())
        {
            return 0;
        }
        else
        {
            return 15;
        }*/
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPotentiometer();
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
        //return this.getDefaultState().withProperty(POWERED, meta);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        //int i = 0;

        /*if (((Boolean)state.getValue(POWERED)).booleanValue())
        {
            i |= 1;
        }*/

        //i = state.getValue(POWERED);

        //return i;
        return state.getValue(FACING).getHorizontalIndex();
    }
}
