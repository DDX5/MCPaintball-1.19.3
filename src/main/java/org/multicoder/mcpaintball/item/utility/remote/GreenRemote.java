package org.multicoder.mcpaintball.item.utility.remote;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.blockinit;
import org.multicoder.mcpaintball.init.soundinit;

import java.util.List;
import java.util.function.Consumer;

public class GreenRemote extends Item
{
    public GreenRemote()
    {
        super(new Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        pPlayer.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            int T = cap.GetTeam();
            if (T == 2) {
                ItemStack stack = pPlayer.getItemInHand(pUsedHand);
                CompoundTag Tag = stack.getOrCreateTag();
                if (pPlayer.isCrouching() && Tag.contains("Targets")) {
                    pLevel.playSound(null, pPlayer.blockPosition(), soundinit.DET.get(), SoundSource.PLAYERS, 0.5f, 1f);
                    ListTag Targets = Tag.getList("Targets", 8);
                    Targets.forEach(
                            tag -> {
                                String[] Pos = tag.getAsString().split(",");
                                BlockPos Position = new BlockPos(Integer.parseInt(Pos[0]), Integer.parseInt(Pos[1]), Integer.parseInt(Pos[2]));
                                pLevel.explode(null, Position.getX(), Position.getY(), Position.getZ(), 5f, Level.ExplosionInteraction.TNT);
                            }
                    );
                    stack.setTag(null);
                }
            }
        });
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        if(Screen.hasShiftDown())
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.c4_remote_spec").withStyle(ChatFormatting.BOLD));
        }
        else
        {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.press_shift").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        ItemStack stack = pContext.getItemInHand();
        CompoundTag Tag = stack.getOrCreateTag();
        BlockPos Pos = pContext.getClickedPos();
        pContext.getPlayer().getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            int Team = cap.GetTeam();
            if (Team == 2) {
                if (!pContext.getPlayer().isCrouching() && pContext.getLevel().getBlockState(Pos).getBlock() == blockinit.GREEN_EXPLOSIVE.get()) {
                    String Position = Pos.getX() + "," + Pos.getY() + "," + Pos.getZ();
                    if (Tag.contains("Targets")) {
                        ListTag Targets = Tag.getList("Targets", 8);
                        StringTag T = StringTag.valueOf(Position);
                        if (Targets.contains(T)) {
                            pContext.getLevel().playSound(null, pContext.getPlayer().blockPosition(), soundinit.REM.get(), SoundSource.PLAYERS, 0.5f, 1f);
                            Targets.remove(T);
                            Tag.put("Targets", Targets);
                        } else {
                            pContext.getLevel().playSound(null, pContext.getPlayer().blockPosition(), soundinit.SET.get(), SoundSource.PLAYERS, 0.5f, 1f);
                            Targets.add(T);
                            Tag.put("Targets", Targets);
                        }
                    } else {
                        pContext.getLevel().playSound(null, pContext.getPlayer().blockPosition(), soundinit.SET.get(), SoundSource.PLAYERS, 0.5f, 1f);
                        ListTag Targets = new ListTag();
                        Targets.add(StringTag.valueOf(Position));
                        Tag.put("Targets", Targets);
                    }
                }
                stack.setTag(Tag);
            }
        });
        return super.useOn(pContext);
    }
}
