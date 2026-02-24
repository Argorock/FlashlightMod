package com.daniel.hooded_lantern.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HoodedLanternBlock extends Block {

    public static final BooleanProperty HOOD = BooleanProperty.create("hood");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE =
            Block.box(5, 0, 5, 11, 9, 11);

    public HoodedLanternBlock(Properties props) {
        super(props);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(HOOD, false)
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HOOD, FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            // Clear beam when block is removed
            clearBeam(level, pos, state.getValue(FACING));
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        boolean hood = state.getValue(HOOD);
        state = state.setValue(HOOD, !hood);
        level.setBlock(pos, state, 3);

        clearBeam(level, pos, state.getValue(FACING));


        if (!hood) {
            createBeam(level, pos, state.getValue(FACING));
        }

        return InteractionResult.SUCCESS;
    }

    private void createBeam(Level level, BlockPos pos, Direction facing) {
        facing = facing.getOpposite();
        for (int i = 1; i <= 15; i++) {
            BlockPos target = pos.relative(facing, i);
            if (level.getBlockState(target).isAir()) {
                level.setBlock(target, ModBlocks.INVISIBLE_LIGHT.get().defaultBlockState(), 3);
            }
        }
    }

    private void clearBeam(Level level, BlockPos pos, Direction facing) {
        facing = facing.getOpposite();
        for (int i = 1; i <= 15; i++) {
            BlockPos target = pos.relative(facing, i);
            if (level.getBlockState(target).is(ModBlocks.INVISIBLE_LIGHT.get())) {
                level.removeBlock(target, false);
            }
        }
    }
}
