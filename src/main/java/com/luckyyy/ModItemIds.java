package com.luckyyy;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemIds {

    public static final ResourceKey<Item> LUCKY_COIN = create("lucky_coin");

    private static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Luckyyy_Coins.id(name));
    }
}