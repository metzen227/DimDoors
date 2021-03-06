package org.dimdev.dimdoors;

import org.dimdev.dimdoors.block.ModBlocks;
import org.dimdev.dimdoors.block.entity.ModBlockEntityTypes;
import org.dimdev.dimdoors.client.ModRendering;
import org.dimdev.dimdoors.entity.ModEntityTypes;
import org.dimdev.dimdoors.entity.MonolithEntity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@Environment(EnvType.CLIENT)
public class DimensionalDoorsClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityTypes.initClient();
        ModRendering.initClient();
        ModBlockEntityTypes.initClient();
        ModBlocks.initClient();

        ClientSidePacketRegistry.INSTANCE.register(DimensionalDoorsInitializer.MONOLITH_PARTICLE_PACKET, MonolithEntity::spawnParticles);
    }
}
