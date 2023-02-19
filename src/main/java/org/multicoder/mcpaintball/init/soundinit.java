package org.multicoder.mcpaintball.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.multicoder.mcpaintball.MCPaintball;

public class soundinit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MCPaintball.MODID);

    public static final RegistryObject<SoundEvent> SINGLE_SHOT = RegSound("single");
    public static final RegistryObject<SoundEvent> BAZOOKA = RegSound("bazooka");

    public static final RegistryObject<SoundEvent> DING = RegSound("ding");
    public static final RegistryObject<SoundEvent> SPLAT = RegSound("splat");

    public static final RegistryObject<SoundEvent> SET = RegSound("set");

    public static final RegistryObject<SoundEvent> REM = RegSound("rem");

    public static final RegistryObject<SoundEvent> DET = RegSound("det");

    public static RegistryObject<SoundEvent> RegSound(String name)
    {
        return SOUNDS.register(name,() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MODID,name),16.0f));
    }
}
