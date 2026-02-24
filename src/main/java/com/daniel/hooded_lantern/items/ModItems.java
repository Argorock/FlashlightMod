package com.daniel.hooded_lantern.items;

import com.daniel.hooded_lantern.HoodedLanternMod;
import com.daniel.hooded_lantern.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HoodedLanternMod.MOD_ID);

    // Correct single registration using your custom item class
    public static final RegistryObject<Item> HOODED_LANTERN_ITEM =
            ITEMS.register("hooded_lantern",
                    () -> new HoodedLanternItem(
                            ModBlocks.HOODED_LANTERN.get(),
                            new Item.Properties()
                    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}