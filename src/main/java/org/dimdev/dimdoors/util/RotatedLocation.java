package org.dimdev.dimdoors.util;

import org.dimdev.annotatednbt.AnnotatedNbt;
import org.dimdev.annotatednbt.AutoSerializable;
import org.dimdev.annotatednbt.Saved;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class RotatedLocation extends Location implements AutoSerializable {
    @Saved
    public final float yaw;
    @Saved
    public final float pitch;

    public RotatedLocation(ServerWorld world, BlockPos pos, float yaw, float pitch) {
        super(world, pos);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static RotatedLocation deserialize(CompoundTag nbt) {
        return AnnotatedNbt.deserialize(RotatedLocation.class, nbt);
    }
}
