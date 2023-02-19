package org.multicoder.mcpaintball.item.utility.kits;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.iteminit;
import org.multicoder.mcpaintball.network.Networking;
import org.multicoder.mcpaintball.network.packets.TeamUpdateS2CPacket;

public class BlueTeamStandard extends Item
{

    public BlueTeamStandard()
    {
        super(new Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        if(!pLevel.isClientSide()) {
            ServerPlayer player = (ServerPlayer) pPlayer;
            player.drop(new ItemStack(iteminit.BLUE_BOOTS.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_LEGGINGS.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_CHESTPLATE.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_HELMET.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_RIFLE.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_PISTOL.get()), true);
            player.drop(new ItemStack(iteminit.BLUE_REMOTE.get()), true);
            player.getItemInHand(pUsedHand).shrink(1);
            player.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                cap.ResetAll();
                cap.SetTeam(3);
                Networking.sendToPlayer(new TeamUpdateS2CPacket(3),(ServerPlayer) player);
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
