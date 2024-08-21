/*
    Dyeable Shulkers   - Dye your Shulkers!

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
    private static final byte NON_DYE_COLOR_ID = 16;
    private static final byte LIGHT_BLUE_DYE = 3;
    private static final byte YELLOW_DYE = 4;
    private static final byte PINK_DYE = 6; // Bocchi chan Color

    public static InteractionResult onPlayerInteractWithShulker(Player player, InteractionHand hand, Entity targetEntity, Level level) {
        if (player.isSpectator() || !(targetEntity instanceof Shulker)) {
            return InteractionResult.PASS;
        }
        //InteractionHand hand = player.getUsedItemHand();
        Item itemInHand = player.getItemInHand(hand).getItem();

        Byte dyeColorID = DyeableShulkers.getColorMap(itemInHand);


        if (dyeColorID == null) {
            return InteractionResult.PASS;
        }

        Byte shulkerColorID = DyeableShulkers.getColorID(targetEntity);
        if (shulkerColorID.equals(dyeColorID)) {
            return InteractionResult.PASS;
        }
        if (player.isCrouching()) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide()) {
            level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
            DyeableShulkers.setColor(targetEntity, dyeColorID);

            ItemStack stack = player.getItemInHand(hand);

            if (!player.isCreative() && dyeColorID > NON_DYE_COLOR_ID) {
                stack.shrink(1);
            }
            if (dyeColorID.equals(NON_DYE_COLOR_ID) && !Items.END_ROD.equals(itemInHand)) {
                level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            if (dyeColorID.equals(NON_DYE_COLOR_ID) && Items.END_ROD.equals(itemInHand)) {
                RandomSource randomSource = level.getRandom();
                boolean randomTrue = randomSource.nextBoolean();
                if (randomTrue) {
                    level.addFreshEntity(new ItemEntity(level,
                            targetEntity.getX(),
                            targetEntity.getY() + 1,
                            targetEntity.getZ(),
                            DyeableShulkers.getItem(shulkerColorID).getDefaultInstance()));
                    if (shulkerColorID == PINK_DYE || shulkerColorID == YELLOW_DYE || shulkerColorID == LIGHT_BLUE_DYE) {
                        level.playSound(null, targetEntity.getOnPos(), SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DyeableShulkers.MOD_ID, "dyeable_shulkers_bocchi_sound")), SoundSource.BLOCKS, 1.0F, 1.0F);

                    } else
                        level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                } else {
                    level.playSound(null, targetEntity.getOnPos(), SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
            DyeableShulkers.setColor(targetEntity, dyeColorID);
            if (!player.isCreative() && dyeColorID < NON_DYE_COLOR_ID) {
                stack.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
