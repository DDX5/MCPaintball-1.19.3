package org.multicoder.mcpaintball;


import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multicoder.mcpaintball.entity.BluePaintballArrowRenderer;
import org.multicoder.mcpaintball.entity.GreenPaintballArrowRenderer;
import org.multicoder.mcpaintball.entity.RedPaintballArrowRenderer;
import org.multicoder.mcpaintball.init.*;
import org.multicoder.mcpaintball.util.BlockHolder;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;

@Mod(MCPaintball.MODID)
public class MCPaintball
{
    public static final String MODID = "mcpaintball";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public MCPaintball()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MCPaintballConfig.SPEC,"mcpaintball-common.toml");
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(this::EntityRenderersSetup);
        bus.addListener(this::OnCommon);
        bus.addListener(this::addCreative);
        iteminit.ITEMS.register(bus);
        blockinit.BLOCKS.register(bus);
        entityinit.ENTITY_TYPES.register(bus);
        soundinit.SOUNDS.register(bus);
    }

    private void EntityRenderersSetup(EntityRenderersEvent.RegisterRenderers event)
    {

        event.registerEntityRenderer((EntityType)entityinit.RED_PAINTBALL.get(), RedPaintballArrowRenderer::new);
        event.registerEntityRenderer((EntityType)entityinit.BLUE_PAINTBALL.get(), BluePaintballArrowRenderer::new);
        event.registerEntityRenderer((EntityType)entityinit.GREEN_PAINTBALL.get(), GreenPaintballArrowRenderer::new);

    }

    private void OnCommon(FMLCommonSetupEvent event)
    {
        BlockHolder.AppendList();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == tabinit.Paintball)
        {
            event.accept(iteminit.BLUE_BOOTS.get());
            event.accept(iteminit.GREEN_BOOTS.get());
            event.accept(iteminit.RED_BOOTS.get());

            event.accept(iteminit.RED_LEGGINGS.get());
            event.accept(iteminit.BLUE_LEGGINGS.get());
            event.accept(iteminit.GREEN_LEGGINGS.get());

            event.accept(iteminit.RED_CHESTPLATE.get());
            event.accept(iteminit.BLUE_CHESTPLATE.get());
            event.accept(iteminit.GREEN_CHESTPLATE.get());

            event.accept(iteminit.RED_HELMET.get());
            event.accept(iteminit.BLUE_HELMET.get());
            event.accept(iteminit.GREEN_HELMET.get());

            event.accept(iteminit.RED_PISTOL.get());
            event.accept(iteminit.BLUE_PISTOL.get());
            event.accept(iteminit.GREEN_PISTOL.get());

            event.accept(iteminit.RED_RIFLE.get());
            event.accept(iteminit.BLUE_RIFLE.get());
            event.accept(iteminit.GREEN_RIFLE.get());

            event.accept(iteminit.RED_SHOTGUN.get());
            event.accept(iteminit.BLUE_SHOTGUN.get());
            event.accept(iteminit.GREEN_SHOTGUN.get());

            event.accept(iteminit.RED_REMOTE.get());
            event.accept(iteminit.BLUE_REMOTE.get());
            event.accept(iteminit.GREEN_REMOTE.get());

            event.accept(blockinit.RED_EXPLOSIVE.get().asItem());
            event.accept(blockinit.BLUE_EXPLOSIVE.get().asItem());
            event.accept(blockinit.GREEN_EXPLOSIVE.get().asItem());

        }
    }
}
