/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShulkerBoxInteractEvent {
    public static InteractionResult onPlayerInteractWithShulkerBox(DyeColor playerDye, Block block, Level level, Player player, InteractionHand hand, ItemStack stack, BlockEntity blockEntity, Item playerItem, BlockState blockState, BlockPos pos) {
        if (!(block instanceof ShulkerBoxBlock shulkerBoxBlock) || !(blockEntity instanceof ShulkerBoxBlockEntity shulkerBoxBlockEntity)) {
            return InteractionResult.PASS;
        }
        if (!player.isCrouching()) {
            return InteractionResult.PASS;
        }
        if (player.isSpectator()) {
            return InteractionResult.PASS;
        }
        if (playerDye == null) {
            return InteractionResult.PASS;
        }
        if (shulkerBoxBlock.getColor() == playerDye) {
            return InteractionResult.PASS;
        }

        CompoundTag nbt = shulkerBoxBlockEntity.saveWithoutMetadata(level.registryAccess());
        DyeColor color = ((DyeItem) playerItem).getDyeColor();

        level.playSound(null, blockEntity.getBlockPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);

        DyeableShulkers.updateShulkerBoxColor(level, pos, color, blockState, shulkerBoxBlockEntity, nbt);
        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
