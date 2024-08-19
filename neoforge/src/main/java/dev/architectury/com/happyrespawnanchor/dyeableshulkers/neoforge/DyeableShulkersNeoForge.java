package dev.architectury.com.happyrespawnanchor.dyeableshulkers.neoforge;

import dev.architectury.com.happyrespawnanchor.dyeableshulkers.DyeableShulkers;
import dev.architectury.com.happyrespawnanchor.dyeableshulkers.neoforge.events.ShulkerInteractEventHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DyeableShulkers.MOD_ID)
public final class DyeableShulkersNeoForge {
    public DyeableShulkersNeoForge(IEventBus modBus) {
        // Run our common setup.
        DyeableShulkers.init();
        NeoForge.EVENT_BUS.register(ShulkerInteractEventHandler.class);
    }
}
