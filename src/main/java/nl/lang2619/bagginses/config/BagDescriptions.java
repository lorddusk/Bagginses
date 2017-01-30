package nl.lang2619.bagginses.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.ModInfo;
import nl.lang2619.bagginses.ModItems;
import nl.lang2619.bagginses.helpers.Bags;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alex on 29/01/2017.
 */
public class BagDescriptions {

    private static ArrayList<BagInfo> descriptions;

    public static void init(File path) {
        File file = new File(path + "/descriptions.json");
        if (!file.exists()) {
            URL inputUrl = Bagginses.class.getResource("/descriptions.json");
            try {
                System.out.println("Creating config file");
                file.createNewFile();
                FileUtils.copyURLToFile(inputUrl, file);
            }
            catch (IOException e) {
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            descriptions = new ArrayList<BagInfo>(Arrays.asList(gson.fromJson(reader, BagInfo[].class)));
            reader.close();
        }
        catch (IOException e) {
            //TODO
        }

        for (BagInfo desc : descriptions) {
            ModItems.bags.get(desc.getColor()).setDesc(desc.getDescription());
        }
    }
}
