package org.multicoder.mcpaintball.item.weapons.shotgun;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.entity.BluePaintballArrowEntity;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;

import java.util.List;

public class BlueShotgunItem extends Item
{

    public BlueShotgunItem()
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
                if(Team == 3) {
                    AbstractArrow AAE1 = new BluePaintballArrowEntity(level, player);
                    AbstractArrow AAE2 = new BluePaintballArrowEntity(level, player);
                    AbstractArrow AAE3 = new BluePaintballArrowEntity(level, player);
                    AAE1.shootFromRotation(player, player.getXRot(), (player.getYRot() + 16.0f), 0.0f, 3.0f, MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
                    AAE2.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
                    AAE3.shootFromRotation(player, player.getXRot(), (player.getYRot() - 16.0f), 0.0f, 3.0f, MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
                    level.addFreshEntity(AAE1);
                    level.addFreshEntity(AAE2);
                    level.addFreshEntity(AAE3);
                    player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(), 25);
                    level.playSound(null,player.blockPosition(), soundinit.SINGLE_SHOT.get(), SoundSource.PLAYERS);
                }
            });
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        if(Screen.hasShiftDown())
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.shotgun_spec").withStyle(ChatFormatting.BOLD));
        }
        else
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.press_shift").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
