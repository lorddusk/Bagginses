package nl.lang2619.bagginses;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import nl.lang2619.bagginses.helpers.Bags;
import nl.lang2619.bagginses.helpers.Util;
import nl.lang2619.bagginses.items.Bag;
import nl.lang2619.bagginses.items.BagType;
import nl.lang2619.bagginses.items.Upgrade;
import nl.lang2619.bagginses.items.recipes.BagRecipe;
import nl.lang2619.bagginses.items.recipes.SoulboundRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 28/01/2017.
 */
public class ModItems {

    public static Map<String, Bags> bags = new HashMap<String, Bags>();
    public static Item upgrade;
    public static Bag foid;
    public static Bag ender;

    public static void init() {
        fillTiers();

        //Do recipe sorting
        RecipeSorter.register(ModInfo.MODID + ":bag", BagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        RecipeSorter.register(ModInfo.MODID + ":soulboundbag", SoulboundRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        //Register the upgrade and recipe for it
        upgrade = new Upgrade();
        registerItem(upgrade, upgrade.getUnlocalizedName());
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.upgrade), "SSS", "III", "WWW", 'S', "string", 'I', "ingotIron", 'W', "plankWood"));

        //Register the void bag and recipe for it
        foid = new Bag("void", BagType.VOID);
        registerItem(foid, foid.getUnlocalizedName());
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', "string", 'w', Blocks.WOOL, 'c', "enderpearl"));

        GameRegistry.addRecipe(new SoulboundRecipe(new ItemStack(foid)));

        //Register the ender bag and recipe for it
        ender = new Bag("ender", BagType.ENDER);
        registerItem(ender, ender.getUnlocalizedName());
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ender), ModItems.upgrade, "chestEnder"));

        GameRegistry.addRecipe(new SoulboundRecipe(new ItemStack(ender)));

        for (Bags bag : bags.values()) {
            if (!bag.isRegistered())
                continue;

            //Tier1
            registerItem(bag.getTier1(), bag.getTier1().getUnlocalizedName());
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bag.getTier1()), "sws", "wcw", "sws", 's', "string", 'w', new ItemStack(Blocks.WOOL, 1, Util.intFromColour(bag.getColor())), 'c', "chestWood"));

            //Tier2
            registerItem(bag.getTier2(), bag.getTier2().getUnlocalizedName());
            GameRegistry.addRecipe(new BagRecipe(new ItemStack(bag.getTier2()), new ItemStack(bag.getTier1()), ModItems.upgrade));

            //Tier3
            registerItem(bag.getTier3(), bag.getTier3().getUnlocalizedName());
            GameRegistry.addRecipe(new BagRecipe(new ItemStack(bag.getTier3()), new ItemStack(bag.getTier2()), ModItems.upgrade, ModItems.upgrade));

            //Soulbound recipe
            GameRegistry.addRecipe(new SoulboundRecipe(new ItemStack(bag.getTier1())));
            GameRegistry.addRecipe(new SoulboundRecipe(new ItemStack(bag.getTier2())));
            GameRegistry.addRecipe(new SoulboundRecipe(new ItemStack(bag.getTier3())));
        }

    }

    static void registerItem(Item item, String name) {
        GameRegistry.register(item, new ResourceLocation(ModInfo.MODID + ":" + name));
    }

    private static void fillTiers() {
        //Register all the colours
        for (EnumDyeColor dyeColour : EnumDyeColor.values()) {
            String colour = dyeColour.getName();
            //TODO register based on config
            bags.put(colour, new Bags(
                    new Bag(colour, BagType.TIER1),
                    new Bag(colour, BagType.TIER2),
                    new Bag(colour, BagType.TIER3),
                    colour,
                    true));
        }
    }
}
