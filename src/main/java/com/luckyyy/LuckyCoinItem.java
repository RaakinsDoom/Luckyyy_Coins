
package com.luckyyy;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.network.chat.Component;

public class LuckyCoinItem extends Item {

    private static final ResourceKey<LootTable> LUCKY_CHEST_LOOT =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    Luckyyy_Coins.id("lucky_chest")
            );

    public LuckyCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();

        if (!(level.getBlockEntity(context.getClickedPos())
                instanceof ChestBlockEntity chest)) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        var player = context.getPlayer();

        if (chest.getLootTable() == null) {
            if (player != null) {
                player.sendSystemMessage(
                        Component.literal("§cThis chest has already been opened!")
                );
            }
            return InteractionResult.FAIL;
        }

        if (LUCKY_CHEST_LOOT.equals(chest.getLootTable())) {
            if (player != null) {
                player.sendSystemMessage(
                        Component.literal("§6This chest has already been blessed with luck!")
                );
            }
            return InteractionResult.FAIL;
        }

        chest.setLootTable(LUCKY_CHEST_LOOT);

        if (player != null) {
            player.sendSystemMessage(
                    Component.literal("§aYour chest has been blessed with luck!")
            );
        }

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
                    context.getClickedPos().getX() + 0.5,
                    context.getClickedPos().getY() + 1.0,
                    context.getClickedPos().getZ() + 0.5,
                    25,
                    0.4,
                    0.5,
                    0.4,
                    0.1
            );

            serverLevel.playSound(
                    null,
                    context.getClickedPos(),
                    SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.BLOCKS,
                    5.0F,
                    1.0F
            );
        }

        ItemStack stack = context.getItemInHand();

        if (player != null && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS_SERVER;
    }
}
