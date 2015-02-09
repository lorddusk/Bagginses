package nl.lang2619.bagginses.references;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.apache.commons.lang3.ArrayUtils;

import java.util.IdentityHashMap;
import java.util.Set;

/**
 * Created by Tim on 8-2-2015.
 * addFromString() taken from chylex with permission
 * Source : https://github.com/chylex/Hardcore-Ender-Expansion
 */
public class BlockList {

    public static IdentityHashMap<Item, short[]> itemList = new IdentityHashMap<Item, short[]>();

    public static boolean contains(Item item, int damage) {
        short[] listedDamages = itemList.get(item);
        return (listedDamages != null && listedDamages.length > 0 && (listedDamages[0] == -1 || ArrayUtils.contains(listedDamages, (short) damage)));
    }

    public static void addFromString(String list) {
        if (list.isEmpty()) return;

        int added = 0;
        String[] split = list.split(",");

        for (String entry : split) {
            entry = entry.trim();
            if (entry.length() == 0) continue;

            String itemName = entry;
            short[] dmgs = new short[]{-1};

            // PARSE DAMAGE VALUES

            if (entry.contains("/")) {
                String[] sep = entry.split("/");
                if (sep.length != 2) {
                    Log.warn("Invalid entry in Decomposition Blacklist: $0", entry);
                    continue;
                }

                itemName = sep[0];
                try {
                    if (sep[1].contains("+")) {
                        String[] dmgVals = sep[1].split("\\+");
                        dmgs = new short[dmgVals.length];
                        for (int a = 0; a < dmgVals.length; a++) dmgs[a] = Short.parseShort(dmgVals[a]);
                    } else dmgs = new short[]{Short.parseShort(sep[1])};
                } catch (NumberFormatException e) {
                    Log.warn("Invalid entry in Decomposition Blacklist, wrong damage values: $0", entry);
                    continue;
                }
            }

            // PARSE ENTRY ID AND NAME

            String[] itemId = itemName.split(":");
            if (itemId.length > 2) {
                Log.warn("Invalid entry in Decomposition Blacklist, wrong item identifier: $0", entry);
                continue;
            } else if (itemId.length == 1) itemId = new String[]{"minecraft", itemId[0]};

            if (itemId[1].equals("*")) { // BLACKLIST ALL BLOCKS AND ITEMS FROM MOD
                String identifier = itemId[0] + ":";

                // SEARCH ALL BLOCKS WITH SPECIFIED ID

                for (String key : (Set<String>) GameData.getBlockRegistry().getKeys()) {
                    if (key.startsWith(identifier)) {
                        Block block = GameData.getBlockRegistry().getRaw(key);

                        if (block == null) {
                            if (itemId[0].equals("minecraft") || Loader.isModLoaded(itemId[0]))
                                Log.warn("Stumbled upon invalid entry in block registry while parsing Decomposition Blacklist, object not found: $0", key);
                            continue;
                        }

                        itemList.put(Item.getItemFromBlock(block), dmgs);
                        ++added;
                    }
                }

                // SEARCH ALL ITEMS WITH SPECIFIED ID

                for (String key : (Set<String>) GameData.getItemRegistry().getKeys()) {
                    if (key.startsWith(identifier)) {
                        Item item = GameData.getItemRegistry().getRaw(key);

                        if (item == null) {
                            if (itemId[0].equals("minecraft") || Loader.isModLoaded(itemId[0]))
                                Log.warn("Stumbled upon invalid entry in item registry while parsing Decomposition Blacklist, object not found: $0", key);
                            continue;
                        }

                        itemList.put(item, dmgs);
                        ++added;
                    }
                }
            } else { // BLACKLIST SPECIFIED ENTRY
                Item item = GameRegistry.findItem(itemId[0], itemId[1]);

                if (item == null) {
                    Block block = GameRegistry.findBlock(itemId[0], itemId[1]);

                    if (block == null) {
                        if (itemId[0].equals("minecraft") || Loader.isModLoaded(itemId[0]))
                            Log.warn("Invalid entry in Decomposition Blacklist, item not found: $0", entry);
                        continue;
                    } else item = Item.getItemFromBlock(block);
                }

                itemList.put(item, dmgs);
                ++added;
            }
        }

        if (added > 0) Log.info("Added $0 items into Decomposition blacklist", added);
    }
}
