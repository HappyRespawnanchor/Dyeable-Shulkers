/*
    Dyeable Shulkers   - Dye your Shulkers!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public final class DyeableShulkers {
    public static final String MOD_ID = "dyeable_shulkers";
    public static final Map<Item, Byte> DYE_COLOR_MAP = new HashMap<>();
    public static final Map<Byte, Item> COLOR_ID_MAP = new HashMap<>();
    public static final Map<Block, DyeColor> SHULKER_BOX_BLOCK_MAP = new HashMap<>();
    public static final Map<DyeColor, Block> SHULKER_BOX_DYE_MAP = new HashMap<>();
    public static final Map<Item, DyeColor> DYE_ITEM_MAP = new HashMap<>();

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

        // 将COLOR_ID_MAP和DYE_ITEM_MAP设置为相反映射
        for (Map.Entry<Item, Byte> entry : DYE_COLOR_MAP.entrySet()) {
            COLOR_ID_MAP.put(entry.getValue(), entry.getKey());
            DYE_ITEM_MAP.put(entry.getKey(), DyeColor.byId(entry.getValue()));
        }
    }

    static {
        SHULKER_BOX_BLOCK_MAP.put(Blocks.WHITE_SHULKER_BOX, DyeColor.WHITE);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.LIGHT_GRAY_SHULKER_BOX, DyeColor.LIGHT_GRAY);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.GRAY_SHULKER_BOX, DyeColor.GRAY);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.BLACK_SHULKER_BOX, DyeColor.BLACK);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.BROWN_SHULKER_BOX, DyeColor.BROWN);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.RED_SHULKER_BOX, DyeColor.RED);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.ORANGE_SHULKER_BOX, DyeColor.ORANGE);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.YELLOW_SHULKER_BOX, DyeColor.YELLOW);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.LIME_SHULKER_BOX, DyeColor.LIME);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.GREEN_SHULKER_BOX, DyeColor.GREEN);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.CYAN_SHULKER_BOX, DyeColor.CYAN);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.LIGHT_BLUE_SHULKER_BOX, DyeColor.LIGHT_BLUE);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.BLUE_SHULKER_BOX, DyeColor.BLUE);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.PURPLE_SHULKER_BOX, DyeColor.PURPLE);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.MAGENTA_SHULKER_BOX, DyeColor.MAGENTA);
        SHULKER_BOX_BLOCK_MAP.put(Blocks.PINK_SHULKER_BOX, DyeColor.PINK);

        // 初始化 SHULKER_BOX_DYE_MAP 为反向映射
        for (Map.Entry<Block, DyeColor> entry : SHULKER_BOX_BLOCK_MAP.entrySet()) {
            SHULKER_BOX_DYE_MAP.put(entry.getValue(), entry.getKey());
        }
    }


    public static void init() {
        // Write common init code here.

    }

    public static void setColor(Entity entity, byte dyeColorID) {
        entity.getEntityData().set(Shulker.DATA_COLOR_ID, dyeColorID);
    }

    public static Byte getColorID(Entity entity) {
        return entity.getEntityData().get(Shulker.DATA_COLOR_ID);
    }

    public static Item getItem(Byte dyeColorID) {
        return COLOR_ID_MAP.get(dyeColorID);
    }

    public static Byte getColorMap(Item itemInHand) {
        return DyeableShulkers.DYE_COLOR_MAP.get(itemInHand);
    }

    public static DyeColor getShulkerBox(Block block) {
        return SHULKER_BOX_BLOCK_MAP.get(block);
    }

    public static Block getShulkerBoxDye(DyeColor color) {
        return SHULKER_BOX_DYE_MAP.get(color);
    }
    public static DyeColor getItemDyeColor(Item item) {
        return DYE_ITEM_MAP.get(item);
    }


}


