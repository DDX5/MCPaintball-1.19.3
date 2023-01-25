package org.multicoder.mcpaintball.item.weapons.shotgun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.entity.GreenPaintballArrowEntity;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.init.tabinit;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;

public class GreenShotgunItem extends Item
{

    public GreenShotgunItem()
    {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if(level.isClientSide())
        {
            player.playSound(soundinit.SINGLE_SHOT.get(),1.0f,1.0f);
        }
        else
        {
            AbstractArrow AAE1 = new GreenPaintballArrowEntity(level,player);
            AbstractArrow AAE2 = new GreenPaintballArrowEntity(level,player);
            AbstractArrow AAE3 = new GreenPaintballArrowEntity(level,player);
            AAE1.shootFromRotation(player,player.getXRot(),(player.getYRot() + 16.0f),0.0f,3.0f, MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
            AAE2.shootFromRotation(player,player.getXRot(),player.getYRot(),0.0f,3.0f,MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
            AAE3.shootFromRotation(player,player.getXRot(),(player.getYRot() - 16.0f),0.0f,3.0f,MCPaintballConfig.SHOTGUN_INACCURACY.get().floatValue());
            level.addFreshEntity(AAE1);
            level.addFreshEntity(AAE2);
            level.addFreshEntity(AAE3);
            player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(),25);
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
