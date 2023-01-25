package org.multicoder.mcpaintball.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.multicoder.mcpaintball.MCPaintball;

import java.awt.*;

@Mod.EventBusSubscriber(modid = MCPaintball.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class tabinit
{
    public static CreativeModeTab Paintball;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        Paintball = event.registerCreativeModeTab(new ResourceLocation(MCPaintball.MODID,"paintball"), builder -> builder.icon(() -> new ItemStack(iteminit.RED_PISTOL.get())).title(Component.translatable("itemGroup.mcpaintball.paintball")).build());

    }
}
