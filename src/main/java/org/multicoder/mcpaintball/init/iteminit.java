package org.multicoder.mcpaintball.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.*;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.item.armor.boots.*;
import org.multicoder.mcpaintball.item.armor.chestplate.*;
import org.multicoder.mcpaintball.item.armor.helmet.*;
import org.multicoder.mcpaintball.item.armor.leggings.*;
import org.multicoder.mcpaintball.item.utility.*;
import org.multicoder.mcpaintball.item.weapons.pistol.*;
import org.multicoder.mcpaintball.item.weapons.rifle.*;
import org.multicoder.mcpaintball.item.weapons.shotgun.*;


public class iteminit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MCPaintball.MODID);

    //  Weapons //
    public static final RegistryObject<Item> RED_PISTOL = ITEMS.register("weapon/pistol/red_pistol", RedPistolItem::new);
    public static final RegistryObject<Item> BLUE_PISTOL = ITEMS.register("weapon/pistol/blue_pistol", BluePistolItem::new);
    public static final RegistryObject<Item> GREEN_PISTOL = ITEMS.register("weapon/pistol/green_pistol", GreenPistolItem::new);

    public static final RegistryObject<Item> RED_SHOTGUN = ITEMS.register("weapon/shotgun/red_shotgun", RedShotgunItem::new);
    public static final RegistryObject<Item> BLUE_SHOTGUN = ITEMS.register("weapon/shotgun/blue_shotgun", BlueShotgunItem::new);
    public static final RegistryObject<Item> GREEN_SHOTGUN = ITEMS.register("weapon/shotgun/green_shotgun", GreenShotgunItem::new);

    public static final RegistryObject<Item> RED_RIFLE = ITEMS.register("weapon/rifle/red_rifle", RedRifleItem::new);
    public static final RegistryObject<Item> BLUE_RIFLE = ITEMS.register("weapon/rifle/blue_rifle", BlueRifleItem::new);
    public static final RegistryObject<Item> GREEN_RIFLE = ITEMS.register("weapon/rifle/green_rifle", GreenRifleItem::new);

    //  Armor //
    public static final RegistryObject<Item> RED_BOOTS = ITEMS.register("armor/red_boots", RedBoots::new);
    public static final RegistryObject<Item> BLUE_BOOTS = ITEMS.register("armor/blue_boots", BlueBoots::new);
    public static final RegistryObject<Item> GREEN_BOOTS = ITEMS.register("armor/green_boots", GreenBoots::new);

    public static final RegistryObject<Item> RED_LEGGINGS = ITEMS.register("armor/red_leggings", RedLeggings::new);
    public static final RegistryObject<Item> BLUE_LEGGINGS = ITEMS.register("armor/blue_leggings", BlueLeggings::new);
    public static final RegistryObject<Item> GREEN_LEGGINGS = ITEMS.register("armor/green_leggings", GreenLeggings::new);

    public static final RegistryObject<Item> RED_CHESTPLATE = ITEMS.register("armor/red_chestplate", RedChestplate::new);
    public static final RegistryObject<Item> BLUE_CHESTPLATE = ITEMS.register("armor/blue_chestplate", BlueChestplate::new);
    public static final RegistryObject<Item> GREEN_CHESTPLATE = ITEMS.register("armor/green_chestplate", GreenChestplate::new);

    public static final RegistryObject<Item> RED_HELMET = ITEMS.register("armor/red_helmet", RedHelmet::new);
    public static final RegistryObject<Item> BLUE_HELMET = ITEMS.register("armor/blue_helmet", BlueHelmet::new);
    public static final RegistryObject<Item> GREEN_HELMET = ITEMS.register("armor/green_helmet", GreenHelmet::new);

    //  Remote //
    public static final RegistryObject<Item> RED_REMOTE = ITEMS.register("utility/red_remote", RedRemote::new);
    public static final RegistryObject<Item> BLUE_REMOTE = ITEMS.register("utility/blue_remote", BlueRemote::new);
    public static final RegistryObject<Item> GREEN_REMOTE = ITEMS.register("utility/green_remote", GreenRemote::new);
    

}
