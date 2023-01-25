package org.multicoder.mcpaintball.item.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import org.multicoder.mcpaintball.init.blockinit;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.init.tabinit;

import java.util.ArrayList;
import java.util.List;

public class GreenRemote extends Item
{
    public GreenRemote()
    {
        super(new Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        if(!pLevel.isClientSide())
        {
            ItemStack stack = pPlayer.getItemInHand(pUsedHand);
            CompoundTag Tag = stack.getTag();
            if(Tag == null)
            {
                return InteractionResultHolder.fail(stack);
            }
            if(Tag.contains("Targets") && pPlayer.isCrouching())
            {
                String[] Targets = Tag.getString("Targets").split("/");
                for(String Target : Targets)
                {
                    String[] Pos = Target.split(",");
                    BlockPos Position = new BlockPos(Integer.parseInt(Pos[0]),Integer.parseInt(Pos[1]),Integer.parseInt(Pos[2]));
                    pLevel.explode(null,Position.getX(),Position.getY(),Position.getZ(),5.0f,  Level.ExplosionInteraction.BLOCK);
                }
                stack.setTag(null);
            }
        }
        else
        {
            if(pPlayer.isCrouching())
            {
                pLevel.playSound(pPlayer,pPlayer.getOnPos(),soundinit.DET.get(), SoundSource.PLAYERS,0.5f,1.0f);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        if(!pContext.getLevel().isClientSide() && !pContext.getPlayer().isCrouching())
        {
            ItemStack stack = pContext.getItemInHand();
            CompoundTag Tag = stack.getTag();
            BlockPos Position = pContext.getClickedPos();
            // Check If Tag Is Null //
            if(Tag == null)
            {
                Tag = new CompoundTag();
            }
            // Check if block is C4 //
            if(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() == blockinit.GREEN_EXPLOSIVE.get())
            {
                // Check If The Target Key Exists
                if(!Tag.contains("Targets"))
                {
                    String Arr = Position.getX() + "," + Position.getY() + "," + Position.getZ();
                    Tag.putString("Targets",Arr);
                }
                // Update The Target Key With New Position //
                else
                {
                    String Arr = Tag.getString("Targets");
                    Arr += "/";
                    Arr += Position.getX() + "," + Position.getY() + "," + Position.getZ();
                    Tag.putString("Targets",Arr);
                }
                // Save Tag To Stack //
                stack.setTag(Tag);
            }
        }
        else
        {
            if(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() == blockinit.GREEN_EXPLOSIVE.get())
            {
                pContext.getLevel().playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(),soundinit.SET.get(),SoundSource.PLAYERS,0.5f,1.0f);
            }
        }
        return super.useOn(pContext);
    }

}
