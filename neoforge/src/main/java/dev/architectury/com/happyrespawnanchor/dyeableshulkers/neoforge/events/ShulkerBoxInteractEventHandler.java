/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers.neoforge.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerBoxInteractEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = DyeableShulkers.MOD_ID)
public class ShulkerBoxInteractEventHandler {
    @SubscribeEvent
    public static void onPlayerInteractWithEntity(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        InteractionHand interactionHand = event.getHand();
        BlockPos pos = event.getPos();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        ItemStack stack = player.getItemInHand(interactionHand);
        Item playerItem = player.getItemInHand(interactionHand).getItem();
        DyeColor playerDye = DyeableShulkers.getItemDyeColor(playerItem);
        InteractionResult interactionResult = ShulkerBoxInteractEvent.onPlayerInteractWithShulkerBox(playerDye, block, level, player, interactionHand, stack, blockEntity, playerItem, blockState, pos);

        if (interactionResult == InteractionResult.SUCCESS) {
            event.setCanceled(true);
            event.setCancellationResult(interactionResult);
        }
    }
}
