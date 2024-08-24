/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ShulkerInteractEvent {
    private static final byte NO_COLOR_ID = 16;
    private static final byte LIGHT_BLUE_DYE = 3;
    private static final byte YELLOW_DYE = 4;
    private static final byte PINK_DYE = 6; // Bocchi chan Color

    public static InteractionResult onPlayerInteractWithShulker(Player player, InteractionHand hand, Entity targetEntity, Level level) {
        if (player.isSpectator() || !(targetEntity instanceof Shulker)) {
            return InteractionResult.PASS;
        }

        ItemStack stack = player.getItemInHand(hand);
        Item itemInHand = stack.getItem();
        Byte dyeColorID = DyeableShulkers.getColorMap(itemInHand);
        TagKey<Item> swordsTag = ItemTags.SWORDS;

        Byte shulkerColorID = DyeableShulkers.getColorID(targetEntity);

        // 如果手持物品无效，或者手持末地烛/剑且目标颜色为16，直接返回 PASS
        if ((dyeColorID == null && !stack.is(swordsTag) && !Items.END_ROD.equals(itemInHand)) ||
                (shulkerColorID.equals(NO_COLOR_ID) && (stack.is(swordsTag) || Items.END_ROD.equals(itemInHand)))) {
            return InteractionResult.PASS;
        }

        if (shulkerColorID.equals(dyeColorID) || player.isCrouching()) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (stack.is(swordsTag)) {
                level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                dyeColorID = NO_COLOR_ID;
            }

            if (Items.END_ROD.equals(itemInHand)) {
                RandomSource randomSource = level.getRandom();
                if (randomSource.nextBoolean()) {
                    level.addFreshEntity(new ItemEntity(level,
                            targetEntity.getX(),
                            targetEntity.getY() + 1,
                            targetEntity.getZ(),
                            DyeableShulkers.getItem(shulkerColorID).getDefaultInstance()));

                    if (shulkerColorID == PINK_DYE || shulkerColorID == YELLOW_DYE || shulkerColorID == LIGHT_BLUE_DYE) {
                        level.playSound(null, targetEntity.getOnPos(),
                                SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DyeableShulkers.MOD_ID, "dyeable_shulkers_bocchi_sound")),
                                SoundSource.BLOCKS, 1.0F, 1.0F);
                        dyeColorID = NO_COLOR_ID;
                    } else {
                        level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
                dyeColorID = NO_COLOR_ID;
            }

            if (!player.isCreative() && !(stack.is(swordsTag) || Items.END_ROD.equals(itemInHand))) {
                stack.shrink(1);
            }
            if(dyeColorID != null) {
                DyeableShulkers.setColor(targetEntity, dyeColorID);

            }
        }

        return InteractionResult.SUCCESS;
    }
}
