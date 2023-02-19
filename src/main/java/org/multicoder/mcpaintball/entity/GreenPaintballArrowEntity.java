package org.multicoder.mcpaintball.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.entityinit;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.network.Networking;
import org.multicoder.mcpaintball.network.packets.TeamPointS2CPacket;
import org.multicoder.mcpaintball.util.BlockHolder;

@SuppressWarnings("all")
public class GreenPaintballArrowEntity extends AbstractArrow
{

    private int life;

    public GreenPaintballArrowEntity(EntityType<? extends Entity> p_36858_, Level p_36859_) {super((EntityType<? extends AbstractArrow>) p_36858_, p_36859_);}

    public GreenPaintballArrowEntity(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {super((EntityType<? extends AbstractArrow>) entityinit.GREEN_PAINTBALL.get(), p_36862_, p_36863_, p_36864_,p_36861_);}

    public GreenPaintballArrowEntity(Level p_36866_, LivingEntity p_36867_) {super((EntityType<? extends AbstractArrow>) entityinit.GREEN_PAINTBALL.get(), p_36867_,p_36866_);}

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
        if(!level.isClientSide)
        {
            if (p_36757_.getEntity() instanceof ServerPlayer)
            {
                ServerPlayer player = (ServerPlayer) getOwner();
                level.playSound(null,player.blockPosition(),soundinit.DING.get(), SoundSource.PLAYERS);
                player.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                    cap.IncPoints();
                    Networking.sendToPlayer(new TeamPointS2CPacket(cap.GetPoints()),player);
                });
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
