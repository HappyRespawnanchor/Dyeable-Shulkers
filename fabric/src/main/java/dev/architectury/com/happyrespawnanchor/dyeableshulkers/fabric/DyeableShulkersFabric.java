package dev.architectury.com.happyrespawnanchor.dyeableshulkers.fabric;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerInteractEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;

public final class DyeableShulkersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DyeableShulkers.init();
        UseEntityCallback.EVENT.register((player, level, interactionHand, entity, entityHitResult) -> {
            Entity targetEntity = entityHitResult != null ? entityHitResult.getEntity() : null;
            return ShulkerInteractEvent.onPlayerInteractWithShulker(player, interactionHand, targetEntity, level);
        });
    }
}
