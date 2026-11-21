

package com.luckyyy;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public class ModBlocks {

    public static final Block LUCKY_ORE = register("lucky_ore");

    private static Block register(String name) {
        ResourceKey<Block> blockKey = ResourceKey.create(
                Registries.BLOCK, Luckyyy_Coins.id(name));

        Block block = new Block(
                BlockBehaviour.Properties.of()
                        .setId(blockKey)
                        .strength(2.0F)
                        .requiresCorrectToolForDrops()
        );

        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM, Luckyyy_Coins.id(name));

        BlockItem blockItem = new BlockItem(
                block,
                new Item.Properties()
                        .setId(itemKey)
                        .useBlockDescriptionPrefix()
        );

        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return block;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
                .register(tab -> tab.accept(LUCKY_ORE.asItem()));
    }
}
