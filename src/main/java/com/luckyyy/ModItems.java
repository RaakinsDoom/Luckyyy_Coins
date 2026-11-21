package com.luckyyy;

import java.util.function.Function;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public class ModItems {

    public static final Item LUCKY_COIN =
            register(ModItemIds.LUCKY_COIN, LuckyCoinItem::new, new Item.Properties());

    private static Item register(
            net.minecraft.resources.ResourceKey<Item> key,
            Function<Item.Properties, Item> factory,
            Item.Properties properties) {

        Item item = factory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(tab -> tab.accept(LUCKY_COIN));
    }
}