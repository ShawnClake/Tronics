package com.shawnclake.tronics;

import com.shawnclake.tronics.creative.CreativeTabTronics;
import com.shawnclake.tronics.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

// https://github.com/Choonster-Minecraft-Mods/TestMod3/tree/1.12/src/main/java/choonster/testmod3/init


@Mod(modid = Tronics.MODID, name = Tronics.NAME, acceptedMinecraftVersions = "[1.12]")
public class Tronics {

    public static final String MODID = "tronics";
    public static final String NAME = "Tronics";

    public static final CreativeTabTronics creativeTab = new CreativeTabTronics();

    @SidedProxy(clientSide = "com.shawnclake.tronics.proxy.ProxyClient", serverSide = "com.shawnclake.tronics.proxy.ProxyServer")
    public static IProxy proxy;

    @Mod.Instance(MODID)
    public static Tronics instance;


    //private Block resistor1k = new BlockPotentiometer(Material.WOOD);

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        //Logger.setLogger(event.getModLog());

        //FMLLog.bigWarning("Random UUID: {}", UUID.randomUUID().toString());

        //ModCapabilities.registerCapabilities();

        //network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

        //ModMessages.registerMessages();
        //ModMapGen.registerMapGen();
        //ModEntities.registerEntities();
        //ModDispenseBehaviors.registerDispenseBehaviors();
        //ModLootTables.registerLootTables();

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        //ModRecipes.registerRecipes();
        //ModMapGen.registerWorldGenerators();
        //ModEntities.addSpawns();

        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        //BlockDumper.dump();

        proxy.postInit();

        //Tests.runTests();
    }





    /*@SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                resistor1k
        );
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new ItemBlock(resistor1k).setRegistryName(resistor1k.getRegistryName())
        );
    }

    @SubscribeEvent
    public void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerVillagerProfessions(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerEntityEntry(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll();
    }*/

}
