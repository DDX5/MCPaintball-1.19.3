package org.multicoder.mcpaintball.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.entity.BluePaintballArrowEntity;
import org.multicoder.mcpaintball.entity.GreenPaintballArrowEntity;
import org.multicoder.mcpaintball.entity.RedPaintballArrowEntity;
import org.multicoder.mcpaintball.item.utility.BlueRemote;

public class entityinit
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MCPaintball.MODID);

    public static final RegistryObject<EntityType<?>> RED_PAINTBALL = ENTITY_TYPES.register("red_paintball",() -> EntityType.Builder.of(RedPaintballArrowEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("red_paintball"));
    public static final RegistryObject<EntityType<?>> BLUE_PAINTBALL = ENTITY_TYPES.register("blue_paintball",() -> EntityType.Builder.of(BluePaintballArrowEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("blue_paintball"));
    public static final RegistryObject<EntityType<?>> GREEN_PAINTBALL = ENTITY_TYPES.register("green_paintball",() -> EntityType.Builder.of(GreenPaintballArrowEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("green_paintball"));


}
