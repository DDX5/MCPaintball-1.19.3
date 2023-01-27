package org.multicoder.mcpaintball.item.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
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
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.init.blockinit;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.init.tabinit;

import java.util.ArrayList;
import java.util.List;

public class RedRemote extends Item
{
    public RedRemote()
    {
        super(new Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag Tag = stack.getOrCreateTag();
        if(pPlayer.isCrouching() && Tag.contains("Targets"))
        {
            pLevel.playSound(pPlayer,pPlayer.blockPosition(),soundinit.DET.get(),SoundSource.PLAYERS,0.5f,1f);
            ListTag Targets = Tag.getList("Targets", 8);
            Targets.forEach(
                    tag -> {
                        String[] Pos = tag.getAsString().split(",");
                        BlockPos Position = new BlockPos(Integer.parseInt(Pos[0]),Integer.parseInt(Pos[1]),Integer.parseInt(Pos[2]));
                        pLevel.explode(null,Position.getX(),Position.getY(),Position.getZ(),5f, Level.ExplosionInteraction.TNT);
                    }
            );
            stack.setTag(null);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        ItemStack stack = pContext.getItemInHand();
        CompoundTag Tag = stack.getOrCreateTag();
        BlockPos Pos = pContext.getClickedPos();
        if(!pContext.getPlayer().isCrouching() && pContext.getLevel().getBlockState(Pos).getBlock() == blockinit.RED_EXPLOSIVE.get())
        {
            String Position = Pos.getX() + "," + Pos.getY() + "," + Pos.getZ();
            if(Tag.contains("Targets"))
            {
                ListTag Targets = Tag.getList("Targets",8);
                MCPaintball.LOG.info("Current Position: " + Position);
                MCPaintball.LOG.info("Positions: " + Targets);
                StringTag T = StringTag.valueOf(Position);
                if(Targets.contains(T))
                {
                    MCPaintball.LOG.info("Contains Position");
                    pContext.getLevel().playSound(pContext.getPlayer(),pContext.getPlayer().blockPosition(),soundinit.REM.get(),SoundSource.PLAYERS,0.5f,1f);
                    Targets.remove(T);
                    Tag.put("Targets",Targets);
                }
                else
                {
                    MCPaintball.LOG.info("Does Not Contain Position");
                    pContext.getLevel().playSound(pContext.getPlayer(),pContext.getPlayer().blockPosition(),soundinit.SET.get(),SoundSource.PLAYERS,0.5f,1f);
                    Targets.add(T);
                    Tag.put("Targets",Targets);
                }
            }
            else
            {
                pContext.getLevel().playSound(pContext.getPlayer(),pContext.getPlayer().blockPosition(),soundinit.SET.get(),SoundSource.PLAYERS,0.5f,1f);
                ListTag Targets = new ListTag();
                Targets.add(StringTag.valueOf(Position));
                Tag.put("Targets",Targets);
            }
        }
        stack.setTag(Tag);
        return super.useOn(pContext);
    }
}
