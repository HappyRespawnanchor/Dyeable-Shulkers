package dev.architectury.com.happyrespawnanchor.dyeableshulkers.neoforge.events;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.events.ShulkerInteractEvent;
import net.minecraft.world.InteractionHand;
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
        InteractionHand interactionHand = player.getUsedItemHand();
        event.setCanceled(true); // md这个坑了我半天，原来要加这个neoforge才会挥手
        event.setCancellationResult(ShulkerInteractEvent.onPlayerInteractWithShulker(player/*, interactionHand*/, targetEntity, level));
    }
}
