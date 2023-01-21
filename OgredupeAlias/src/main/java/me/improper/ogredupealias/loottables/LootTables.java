package me.improper.ogredupealias.loottables;

import me.improper.ogredupealias.items.CustomItem;
import org.bukkit.Material;

public class LootTables {

    public static void registerTables() {
        setSieveGravel();
        setSieveSand();
        setSieveDirt();
    }

    public static UncertainLoot SIEVE_GRAVEL;
    public static UncertainLoot SIEVE_SAND;
    public static UncertainLoot SIEVE_DIRT;

    static void setSieveGravel() {
        UncertainLoot loot = new UncertainLoot();
        loot.setIterations(2);
        for (int i = 0; i < 30; i ++) loot.addItem(new CustomItem(Material.FLINT));
        loot.addItem(new CustomItem(Material.COAL));
        for (int i = 0; i < 30; i ++) loot.addItem(new CustomItem(Material.COBBLESTONE));
        loot.addItem(new CustomItem(Material.IRON_NUGGET));
        loot.addItem(new CustomItem(Material.CLAY_BALL));
        SIEVE_GRAVEL = loot;
    }

    static void setSieveSand() {
        UncertainLoot loot = new UncertainLoot();
        loot.setIterations(1);
        for (int i = 0; i < 30; i ++) loot.addItem(new CustomItem(Material.DEAD_BUSH));
        for (int i = 0; i < 30; i ++) loot.addItem(new CustomItem(Material.SANDSTONE));
        loot.addItem(new CustomItem(Material.STRING));
        loot.addItem(new CustomItem(Material.DIAMOND));
        loot.addItem(new CustomItem(Material.EXPERIENCE_BOTTLE));
        loot.addItem(new CustomItem(Material.GOLD_NUGGET));
        SIEVE_SAND = loot;
    }

    static void setSieveDirt() {
        UncertainLoot loot = new UncertainLoot();
        loot.setIterations(3);
        loot.addItem(new CustomItem(Material.BAMBOO));
        for (int i = 0; i < 5; i ++) loot.addItem(new CustomItem(Material.STICK));
        loot.addItem(new CustomItem(Material.STRING));
        loot.addItem(new CustomItem(Material.VINE));
        loot.addItem(new CustomItem(Material.BEETROOT_SEEDS));
        loot.addItem(new CustomItem(Material.POTATO));
        loot.addItem(new CustomItem(Material.CARROT));
        loot.addItem(new CustomItem(Material.BONE_MEAL));
        for (int i = 0; i < 5; i ++) loot.addItem(new CustomItem(Material.WHEAT_SEEDS));
        for (int i = 0; i < 5; i ++) loot.addItem(new CustomItem(Material.GRASS));
        loot.addItem(new CustomItem(Material.MOSS_BLOCK));
        loot.addItem(new CustomItem(Material.OAK_SAPLING));
        loot.addItem(new CustomItem(Material.KELP));
        loot.addItem(new CustomItem(Material.SUGAR_CANE));
        loot.addItem(new CustomItem(Material.DANDELION));
        loot.addItem(new CustomItem(Material.LILY_PAD));
        SIEVE_DIRT = loot;
    }
}
