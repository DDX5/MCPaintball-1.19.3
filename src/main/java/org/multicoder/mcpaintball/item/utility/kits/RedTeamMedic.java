package org.multicoder.mcpaintball.item.utility.kits;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.iteminit;
import org.multicoder.mcpaintball.network.Networking;
import org.multicoder.mcpaintball.network.packets.TeamUpdateS2CPacket;

import java.util.List;

public class RedTeamMedic extends Item
{

    public RedTeamMedic()
    {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        if(!pLevel.isClientSide()) {
            ServerPlayer player = (ServerPlayer) pPlayer;
            player.drop(new ItemStack(iteminit.RED_BOOTS.get()), true);
            player.drop(new ItemStack(iteminit.RED_LEGGINGS.get()), true);
            player.drop(new ItemStack(iteminit.RED_CHESTPLATE.get()), true);
            player.drop(new ItemStack(iteminit.RED_HELMET.get()), true);
            player.drop(new ItemStack(iteminit.RED_SHOTGUN.get()), true);
            player.drop(new ItemStack(iteminit.RED_PISTOL.get()), true);
            player.drop(new ItemStack(iteminit.RED_REMOTE.get()), true);
            player.drop(new ItemStack(iteminit.TABLET.get()),true);
            player.getItemInHand(pUsedHand).shrink(1);
            player.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                cap.ResetAll();
                cap.SetTeam(1);
                Networking.sendToPlayer(new TeamUpdateS2CPacket(1),(ServerPlayer) player);
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        if(Screen.hasShiftDown())
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.click_medic").withStyle(ChatFormatting.BOLD));
        }
        else
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.press_shift").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
