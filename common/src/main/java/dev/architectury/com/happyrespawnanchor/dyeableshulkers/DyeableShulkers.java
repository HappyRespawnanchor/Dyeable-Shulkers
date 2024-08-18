package dev.architectury.com.happyrespawnanchor.dyeableshulkers;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public final class DyeableShulkers {
    public static final String MOD_ID = "dye_my_shulkers";

    public static void init() {
        // Write common init code here.

    }
    public static final Map<Item, Byte> DYE_COLOR_MAP = new HashMap<>();
    static {
        DYE_COLOR_MAP.put(Items.WHITE_DYE, (byte) 0);
        DYE_COLOR_MAP.put(Items.ORANGE_DYE, (byte) 1);
        DYE_COLOR_MAP.put(Items.MAGENTA_DYE, (byte) 2);
        DYE_COLOR_MAP.put(Items.LIGHT_BLUE_DYE, (byte) 3);
        DYE_COLOR_MAP.put(Items.YELLOW_DYE, (byte) 4);
        DYE_COLOR_MAP.put(Items.LIME_DYE, (byte) 5);
        DYE_COLOR_MAP.put(Items.PINK_DYE, (byte) 6);
        DYE_COLOR_MAP.put(Items.GRAY_DYE, (byte) 7);
        DYE_COLOR_MAP.put(Items.LIGHT_GRAY_DYE, (byte) 8);
        DYE_COLOR_MAP.put(Items.CYAN_DYE, (byte) 9);
        DYE_COLOR_MAP.put(Items.PURPLE_DYE, (byte) 10);
        DYE_COLOR_MAP.put(Items.BLUE_DYE, (byte) 11);
        DYE_COLOR_MAP.put(Items.BROWN_DYE, (byte) 12);
        DYE_COLOR_MAP.put(Items.GREEN_DYE, (byte) 13);
        DYE_COLOR_MAP.put(Items.RED_DYE, (byte) 14);
        DYE_COLOR_MAP.put(Items.BLACK_DYE, (byte) 15);
        // DYE_COLOR_MAP.put(Items.WATER_BUCKET, (byte) 16);
    }

    public static void setColor(Entity entity, byte color) {
        entity.getEntityData().set(Shulker.DATA_COLOR_ID, color);
    }
}


