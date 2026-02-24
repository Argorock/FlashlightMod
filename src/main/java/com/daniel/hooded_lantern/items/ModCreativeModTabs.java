package com.daniel.hooded_lantern.items;

import com.daniel.hooded_lantern.HoodedLanternMod;
import com.daniel.hooded_lantern.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HoodedLanternMod.MOD_ID);


    public static final RegistryObject<CreativeModeTab> HOODED_LANTERN_TAB = CREATIVE_MODE_TAB.register("hooded_lantern_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.HOODED_LANTERN.get().asItem()))
                    .title(Component.translatable("creativetab.hooded_lantern_tab"))
                    .displayItems((pParameters, poutput) -> {
//                        poutput.accept(ModItems.HOODEDLANTERN.get());

                        poutput.accept(ModBlocks.HOODED_LANTERN.get().asItem());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register((eventBus));
    }
}
