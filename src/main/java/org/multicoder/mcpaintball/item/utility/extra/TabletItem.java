package org.multicoder.mcpaintball.item.utility.extra;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;

public class TabletItem extends Item
{

    public TabletItem()
    {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        if(!pLevel.isClientSide())
        {
            pPlayer.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                pPlayer.sendSystemMessage(Component.literal("Current Points: " + cap.GetPoints()).withStyle(ChatFormatting.DARK_RED));
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
