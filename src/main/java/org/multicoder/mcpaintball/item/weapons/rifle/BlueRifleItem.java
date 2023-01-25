package org.multicoder.mcpaintball.item.weapons.rifle;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.entity.BluePaintballArrowEntity;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.init.tabinit;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;

public class BlueRifleItem extends Item
{

    public BlueRifleItem()
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
            AbstractArrow AAE = new BluePaintballArrowEntity(level,player);
            AAE.shootFromRotation(player,player.getXRot(),player.getYRot(),0.0f,3.0f, MCPaintballConfig.PISTOL_INACCURACY.get().floatValue());
            level.addFreshEntity(AAE);
            player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(),5);
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
