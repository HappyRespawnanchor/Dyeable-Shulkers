/*
    Dyeable Shulkers   - Dye your Shulker & Shulker Box!

    Written in 2024 by HappyRespawnanchor happyrespawnanchor@gmail.com

    Icon Designer: Yeettee

    To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.

    You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.architectury.com.happyrespawnanchor.dyeableshulkers;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.HashMap;
import java.util.Map;

public final class DyeableShulkers {
    public static final String MOD_ID = "dyeable_shulkers";
    private static final Map<Item, Byte> DYE_COLOR_MAP = new HashMap<>();
    private static final Map<Byte, Item> COLOR_ID_MAP = new HashMap<>();
    private static final Map<Item, DyeColor> DYE_ITEM_MAP = new HashMap<>();
    private static final Map<Integer, BlockState> SHULKER_MAP = new HashMap<>();

    static {
        SHULKER_MAP.put(0, Blocks.WHITE_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(1, Blocks.ORANGE_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(2, Blocks.MAGENTA_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(3, Blocks.LIGHT_BLUE_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(4, Blocks.YELLOW_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(5, Blocks.LIME_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(6, Blocks.PINK_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(7, Blocks.GRAY_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(8, Blocks.LIGHT_GRAY_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(9, Blocks.CYAN_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(10, Blocks.PURPLE_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(11, Blocks.BLUE_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(12, Blocks.BROWN_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(13, Blocks.GREEN_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(14, Blocks.RED_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(15, Blocks.BLACK_SHULKER_BOX.defaultBlockState());
        SHULKER_MAP.put(16, Blocks.SHULKER_BOX.defaultBlockState());
    }

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
//        DYE_COLOR_MAP.put(Items.WOODEN_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.STONE_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.IRON_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.GOLDEN_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.DIAMOND_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.NETHERITE_SWORD,  16);
//        DYE_COLOR_MAP.put(Items.END_ROD,  16);

        // 将COLOR_ID_MAP和DYE_ITEM_MAP设置为相反映射
        for (Map.Entry<Item, Byte> entry : DYE_COLOR_MAP.entrySet()) {
            COLOR_ID_MAP.put(entry.getValue(), entry.getKey());
            DYE_ITEM_MAP.put(entry.getKey(), DyeColor.byId(entry.getValue()));
        }
    }


    public static void init() {
        // Write common init code here.

    }

    public static void setColor(Entity entity, byte dyeColorID) {
        entity.getEntityData().set(Shulker.DATA_COLOR_ID, dyeColorID);
    }

    //
    public static Byte getColorID(Entity entity) {
        return entity.getEntityData().get(Shulker.DATA_COLOR_ID);
    }

    //
    public static Item getItem(Byte dyeColorID) {
        return COLOR_ID_MAP.get(dyeColorID);
    }

    //
    public static Byte getColorMap(Item itemInHand) {
        return DyeableShulkers.DYE_COLOR_MAP.get(itemInHand);
    }

    public static DyeColor getItemDyeColor(Item item) {
        return DYE_ITEM_MAP.get(item);
    }

    public static BlockState getShulkerId(int color) {
        return SHULKER_MAP.get(color);
    }

    public static void updateShulkerBoxColor(Level level, BlockPos pos, DyeColor color, BlockState blockState, ShulkerBoxBlockEntity shulkerBoxBlockEntity, CompoundTag nbt) {
        level.setBlockAndUpdate(pos, getShulkerId(color.getId()).setValue(BlockStateProperties.FACING, blockState.getValue(BlockStateProperties.FACING)));
        ShulkerBoxBlockEntity newShulkerBox = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
        if (newShulkerBox != null) {
            newShulkerBox.loadWithComponents(nbt, level.registryAccess());
        }
    }


}


