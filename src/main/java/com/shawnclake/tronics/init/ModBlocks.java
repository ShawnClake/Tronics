package com.shawnclake.tronics.init;

import com.google.common.base.Preconditions;
import com.shawnclake.tronics.Tronics;
import com.shawnclake.tronics.common.blocks.BlockPotentiometer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Tronics.MODID)
public class ModBlocks {

    public static final BlockPotentiometer POTENTIOMETER = new BlockPotentiometer();

    @Mod.EventBusSubscriber(modid = Tronics.MODID)
    public static class RegistrationHandler {
        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            final Block[] blocks = {
                    POTENTIOMETER

            };

            registry.registerAll(blocks);
        }


        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

            final IForgeRegistry<Item> registry = event.getRegistry();

            final ItemBlock[] items = {
                new ItemBlock(POTENTIOMETER)

            };

            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }

        }


    }
}
