package com.zixiken.dimdoors.shared.items;

import com.zixiken.dimdoors.shared.blocks.IRiftProvider;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemDimensionalDoor extends ItemDoor {

    public <T extends Block & IRiftProvider<?>>ItemDimensionalDoor(T block) {
        super(block);
    }

    // TODO: endermen/block placers should set up blocks too, but this method doesn't get called when they place the block
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumActionResult result = super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
        if (result == EnumActionResult.SUCCESS) {
            if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) pos = pos.offset(facing);
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof IRiftProvider<?>) { // In case the door is placed on glass/leaves
                ((IRiftProvider<?>) state.getBlock()).handleRiftSetup(world, pos, state);
            }
        }
        return result;
    }
}
