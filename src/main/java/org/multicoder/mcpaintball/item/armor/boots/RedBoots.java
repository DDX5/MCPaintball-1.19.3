package org.multicoder.mcpaintball.item.armor.boots;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.init.tabinit;
import org.multicoder.mcpaintball.util.PaintballArmor;

public class RedBoots extends ArmorItem
{

    public RedBoots()
    {
        super(PaintballArmor.Red, EquipmentSlot.FEET, new Properties());
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        stack.setDamageValue(0);
        super.onArmorTick(stack, level, player);
    }
}
