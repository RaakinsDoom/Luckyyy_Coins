
package com.luckyyy;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModWorldGeneration {

    private static final ResourceKey<PlacedFeature> LUCKY_ORE_PLACED =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    Luckyyy_Coins.id("lucky_ore")
            );

    public static void initialize() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                LUCKY_ORE_PLACED
        );
    }
}
