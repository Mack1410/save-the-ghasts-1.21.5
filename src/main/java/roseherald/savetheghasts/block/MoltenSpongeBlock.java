package roseherald.savetheghasts.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MoltenSpongeBlock extends MagmaBlock implements FluidDrainable {

    public MoltenSpongeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack tryDrainFluid(@Nullable LivingEntity drainer, WorldAccess world, BlockPos pos, BlockState state) {

        world.setBlockState(pos, ModBlocks.DESICCATED_SPONGE.getDefaultState(), Block.NOTIFY_LISTENERS);

        return new ItemStack(Items.LAVA_BUCKET);
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Fluids.LAVA.getBucketFillSound();
    }
}
