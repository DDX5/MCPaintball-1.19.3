package org.multicoder.mcpaintball.item.weapons.pistol;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.entity.GreenPaintballArrowEntity;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;

public class GreenPistolItem extends Item
{

    public GreenPistolItem()
    {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if(!level.isClientSide)
        {
            player.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->
            {
                int Team = cap.GetTeam();
                if (Team == 2) {
                    AbstractArrow AAE = new GreenPaintballArrowEntity(level, player);
                    AAE.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, MCPaintballConfig.PISTOL_INACCURACY.get().floatValue());
                    level.addFreshEntity(AAE);
                    player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(), 20);
                    level.playSound(null,player.blockPosition(), soundinit.SINGLE_SHOT.get(),SoundSource.PLAYERS);
                }
            });
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
