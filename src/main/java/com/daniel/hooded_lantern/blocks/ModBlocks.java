package com.daniel.hooded_lantern.blocks;

import com.daniel.hooded_lantern.HoodedLanternMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HoodedLanternMod.MOD_ID);

    // Invisible light block for directional beam
    public static final RegistryObject<Block> INVISIBLE_LIGHT =
            BLOCKS.register("invisible_light",
                    () -> new Block(BlockBehaviour.Properties.of()
                            .noCollission()
                            .noOcclusion()
                            .lightLevel(s -> 15)
                            .air()
                    ));

    // Hooded lantern block
    public static final RegistryObject<Block> HOODED_LANTERN =
            BLOCKS.register("hooded_lantern",
                    () -> new HoodedLanternBlock(
                            BlockBehaviour.Properties.copy(Blocks.LANTERN)
                                    .lightLevel(state -> state.getValue(HoodedLanternBlock.HOOD) ? 0 : 15)
                    ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}