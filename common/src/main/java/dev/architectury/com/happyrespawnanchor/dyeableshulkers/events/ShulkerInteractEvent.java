package dev.architectury.com.happyrespawnanchor.dyeableshulkers.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShulkerInteractEvent {
    public static InteractionResult onPlayerInteractWithShulker(Player player, InteractionHand hand, Entity targetEntity, Level level) {
        if (player.isSpectator() || !(targetEntity instanceof Shulker)) {
            return InteractionResult.FAIL;
        }
        Item mainHandDyes = player.getMainHandItem().getItem();

        Byte dyeColorID = DyeableShulkers.DYE_COLOR_MAP.get(mainHandDyes);

        if (dyeColorID == null) {
            return InteractionResult.FAIL;
        }

        Byte currentColorID = DyeableShulkers.getColorID(targetEntity);
        if (currentColorID.equals(dyeColorID)) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            level.playSound(null, targetEntity.getOnPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            DyeableShulkers.setColor(targetEntity, dyeColorID);

            ItemStack stack = player.getItemInHand(hand);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }


        return InteractionResult.SUCCESS;
    }
}
