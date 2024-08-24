/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

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
