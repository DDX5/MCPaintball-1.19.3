package org.multicoder.mcpaintball.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.multicoder.mcpaintball.init.entityinit;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.util.BlockHolder;

public class RedPaintballArrowEntity extends AbstractArrow
{

    public RedPaintballArrowEntity(EntityType<? extends Entity> p_36858_, Level p_36859_) {super((EntityType<? extends AbstractArrow>) p_36858_, p_36859_);}

    public RedPaintballArrowEntity(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {super((EntityType<? extends AbstractArrow>) entityinit.RED_PAINTBALL.get(), p_36862_, p_36863_, p_36864_,p_36861_);}

    public RedPaintballArrowEntity(Level p_36866_, LivingEntity p_36867_) {super((EntityType<? extends AbstractArrow>) entityinit.RED_PAINTBALL.get(), p_36867_,p_36866_);}

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return soundinit.SPLAT.get();
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_)
    {
        if(level.isClientSide)
        {
            if (p_36757_.getEntity() instanceof Cow)
            {
                Player player = (Player) getOwner();
                player.playSound(soundinit.DING.get());
            }
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult)
    {
        if(!level.isClientSide)
        {
            BlockPos position = pResult.getBlockPos();
            Block block = level.getBlockState(position).getBlock();
            if(BlockHolder.BREAKABLES.contains(block))level.setBlockAndUpdate(position, Blocks.AIR.defaultBlockState());
            else super.onHitBlock(pResult);
        }
    }
}
