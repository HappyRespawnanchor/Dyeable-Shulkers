/*
    Dyeable Shulkers   - Dye your Shulkers!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

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
