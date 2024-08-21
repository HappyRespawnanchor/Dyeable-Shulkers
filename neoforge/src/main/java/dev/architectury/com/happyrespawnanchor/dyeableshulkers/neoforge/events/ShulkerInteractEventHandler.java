/*
    Dyeable Shulkers   - Dye your Shulkers!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers.neoforge.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerInteractEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = DyeableShulkers.MOD_ID)
public class ShulkerInteractEventHandler {
    @SubscribeEvent
    public static void onPlayerInteractWithEntity(PlayerInteractEvent.EntityInteract event) {
        Entity targetEntity = event.getTarget();

        Player player = event.getEntity();
        Level level = event.getLevel();
        InteractionHand interactionHand = event.getHand();
        if(ShulkerInteractEvent.onPlayerInteractWithShulker(player,interactionHand ,targetEntity, level) == InteractionResult.SUCCESS){
            event.setCanceled(true); // 这个坑了我半天，原来要加这个neoforge才会挥手
            event.setCancellationResult(ShulkerInteractEvent.onPlayerInteractWithShulker(player, interactionHand,targetEntity, level));
        }
    }
}
