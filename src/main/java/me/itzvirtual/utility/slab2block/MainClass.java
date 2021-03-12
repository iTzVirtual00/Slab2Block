package me.itzvirtual.utility.slab2block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MainClass extends JavaPlugin {
    Plugin plugin;

    List<NamespacedKey> keys = new ArrayList<>();


    @Override
    public void onEnable() {
        plugin = this;
        List<String> names = new ArrayList<>();
        for(Material k : Material.values()){
            names.add(k.name());
        }

        for(Material i : Material.values()){
            String name = i.name();
            if(!name.endsWith("_SLAB") || name.startsWith("PETRIFIED")){
                continue;
            }
            name = name.replace("_SLAB", "");
            Material m;


            if(names.contains(name)){
                m = Material.valueOf(name);
            }
            else if(names.contains(name + "_PLANKS")){
                m = Material.valueOf(name + "_PLANKS");
            }
            else if(names.contains(name + "_BLOCK")){
                m = Material.valueOf(name + "_BLOCK");
            }
            else if(names.contains(name + "S")){
                m = Material.valueOf(name + "S");
            }
            else {
                Bukkit.getLogger().warning( "[Slab2Block] " + name + " FAILED");
                continue;
            }

            ItemStack item = new ItemStack(m, 1);



            NamespacedKey nKey = new NamespacedKey(plugin, "Slab2Block_" + m.name());
            keys.add(nKey);
            ShapelessRecipe recipe = new ShapelessRecipe(nKey, item);
            recipe.addIngredient(2, i);
            Bukkit.addRecipe(recipe);


        }
    }


    @Override
    public void onDisable() {
        for(NamespacedKey i : keys){
            Bukkit.removeRecipe(i);
        }
    }
}
