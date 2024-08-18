package dev.architectury.com.happyrespawnanchor.dyeableshulkers.fabric;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.Item;

import static dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers.DYE_COLOR_MAP;

public final class DyeableShulkersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DyeableShulkers.init();

        UseEntityCallback.EVENT.register((player, level, interactionHand, entity, entityHitResult) -> {
            if (!level.isClientSide() && entityHitResult != null && !player.isSpectator() && entityHitResult.getEntity() instanceof Shulker) {
                Item mainHandDyes = player.getMainHandItem().getItem();
                Byte dyeColorID = DYE_COLOR_MAP.get(mainHandDyes);
                if (dyeColorID != null) {
                    DyeableShulkers.setColor(entityHitResult.getEntity(), dyeColorID);
                }
            }
            return InteractionResult.SUCCESS;
        });
    }


}