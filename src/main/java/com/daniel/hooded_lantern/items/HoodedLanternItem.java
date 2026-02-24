package com.daniel.hooded_lantern.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;   // <-- REQUIRED
import net.minecraft.nbt.CompoundTag;
import top.theillusivec4.curios.api.type.capability.ICurioItem;


public class HoodedLanternItem extends BlockItem implements ICurioItem {

    public HoodedLanternItem(Block block, Item.Properties props) {
        super(block, props);
    }


    public class LanternItem extends Item implements ICurioItem {
        public LanternItem(Properties props) {
            super(props);
        }
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        CompoundTag tag = stack.getOrCreateTag();
        boolean hood = tag.getBoolean("Hood");
        tag.putBoolean("Hood", !hood);

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}