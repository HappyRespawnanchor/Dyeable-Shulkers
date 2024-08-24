/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers.fabric;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerBoxInteractEvent;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerInteractEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class DyeableShulkersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DyeableShulkers.init();

        UseEntityCallback.EVENT.register((player, level, interactionHand, entity, entityHitResult) -> {
            Entity targetEntity = entityHitResult != null ? entityHitResult.getEntity() : null;
            return ShulkerInteractEvent.onPlayerInteractWithShulker(player, interactionHand, targetEntity, level);
        });

        UseBlockCallback.EVENT.register((player, level, interactionHand, blockHitResult) -> {
            BlockPos pos = blockHitResult.getBlockPos();
            Item playerItem = player.getItemInHand(interactionHand).getItem();
            DyeColor playerDye = DyeableShulkers.getItemDyeColor(playerItem);
            BlockEntity blockEntity = level.getBlockEntity(pos);
            BlockState blockState = level.getBlockState(pos);
            Block block = blockState.getBlock();
            ItemStack stack = player.getItemInHand(interactionHand);
            return ShulkerBoxInteractEvent.onPlayerInteractWithShulkerBox(playerDye, block, level, player, interactionHand, stack, blockEntity, playerItem, blockState, pos);
        });
    }

}
