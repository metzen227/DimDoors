package com.zixiken.dimdoors.shared.rifts;

import ddutils.Location;
import ddutils.nbt.NBTUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3i;

@Getter @AllArgsConstructor @Builder(toBuilder = true) @ToString
public class RelativeDestination extends RiftDestination { // TODO: use Vec3i
    private Vec3i offset;

    public RelativeDestination() {}

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        offset = NBTUtils.readVec3i(nbt.getCompoundTag("offset"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        nbt.setTag("offset", NBTUtils.writeVec3i(offset));
        return nbt;
    }

    @Override
    public boolean teleport(TileEntityRift rift, Entity entity) {
        rift.getWorld().getTileEntity(rift.getPos().add(offset));
        return true;
    }

    @Override
    public Location getReferencedRift(Location rift) {
        return new Location(rift.getDim(), rift.getPos().add(offset));
    }
}
