package dev.architectury.com.happyrespawnanchor.dyeableshulkers.fabric;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerInteractEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
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
            BlockState state = level.getBlockState(pos);
            Item playerItem = player.getItemInHand(interactionHand).getItem();
            // 获取当前方块的颜色，如果方块不是 Shulker Box 则返回 null
            DyeColor color = DyeableShulkers.SHULKER_BOX_BLOCK_MAP.get(state.getBlock());
            if(!player.isCrouching()){
                return InteractionResult.PASS;
            }
            if(!level.isClientSide()){
                if (!player.isSpectator() && (state.getBlock() == Blocks.SHULKER_BOX || state.getBlock() == Blocks.RED_SHULKER_BOX || state.getBlock() == Blocks.CYAN_SHULKER_BOX) && player.isCrouching()) {
                    if (level.getBlockEntity(pos) instanceof ShulkerBoxBlockEntity shulkerBox) {
                        if (color != null) {
                            // 设置 Shulker 盒子的颜色
                            ShulkerBoxBlockEntity shulkerBoxBlockEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
                            shulkerBoxBlockEntity.color = color;
                        }
                    }
                }
            }
            return InteractionResult.SUCCESS;
        });
    }
}
