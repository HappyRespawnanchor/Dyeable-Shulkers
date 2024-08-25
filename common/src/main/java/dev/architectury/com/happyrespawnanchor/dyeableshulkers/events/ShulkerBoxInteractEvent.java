package dev.architectury.com.happyrespawnanchor.dyeableshulkers.events;
/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShulkerBoxInteractEvent {
    public static InteractionResult onPlayerInteractWithShulkerBox(DyeColor playerDye, Block block, Level level, Player player, InteractionHand hand, ItemStack stack, BlockEntity blockEntity, Item playerItem, BlockState blockState, BlockPos pos) {
        TagKey<Item> swordTag = ItemTags.SWORDS;
        if (!(block instanceof ShulkerBoxBlock shulkerBoxBlock) || !(blockEntity instanceof ShulkerBoxBlockEntity shulkerBoxBlockEntity)) {
            return InteractionResult.PASS;
        }
        if (player.isSpectator()) {
            return InteractionResult.PASS;
        }
        if (playerDye == null && !(stack.is(swordTag) || stack.is(Items.END_ROD))) {
            return InteractionResult.PASS;
        }

        if (!player.isCrouching()) {
            return InteractionResult.PASS;
        }

        //DyeColor color = ((DyeItem) playerItem).getDyeColor();
        if (shulkerBoxBlock.getColor() == playerDye) {
            return InteractionResult.PASS;
        }

        if (!shulkerBoxBlockEntity.isClosed()) {
            return InteractionResult.PASS;
        }
        // 如果玩家手持铁剑，清除 Shulker Box 的颜色
        if (stack.is(swordTag) || stack.is(Items.END_ROD)) {
            if (shulkerBoxBlock.defaultBlockState().equals(Blocks.SHULKER_BOX.defaultBlockState())) {
                return InteractionResult.PASS;
            }

            CompoundTag nbt = shulkerBoxBlockEntity.saveWithoutMetadata(level.registryAccess());
            DyeableShulkers.updateShulkerBoxNoneColor(level, pos, blockState, shulkerBoxBlockEntity, nbt);
            if (stack.is(swordTag)) {
                level.playSound(null, blockEntity.getBlockPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.playSound(null, blockEntity.getBlockPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    //stack.shrink(1);
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                }
                return InteractionResult.SUCCESS;
            }
            if (stack.is(Items.END_ROD)) {
                level.playSound(null, blockEntity.getBlockPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(shulkerBoxBlock.getColor() == DyeColor.LIGHT_BLUE || shulkerBoxBlock.getColor() == DyeColor.PINK || shulkerBoxBlock.getColor() == DyeColor.YELLOW){
                    RandomSource randomSource = level.getRandom();
                    if (randomSource.nextBoolean()) {
                        level.playSound(null, blockEntity.getBlockPos(),
                                SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DyeableShulkers.MOD_ID, "dyeable_shulkers_bocchi_shakai_ga_kowai_sound")),
                                SoundSource.BLOCKS, 1.0F, 1.0F);


                        return InteractionResult.PASS;

                    }
                }


            }
            return InteractionResult.PASS;
        }


        level.playSound(null, blockEntity.getBlockPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
        DyeColor color = ((DyeItem) playerItem).getDyeColor();
        CompoundTag nbt = shulkerBoxBlockEntity.saveWithoutMetadata(level.registryAccess());
        DyeableShulkers.updateShulkerBoxColor(level, pos, color, blockState, shulkerBoxBlockEntity, nbt);
        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
