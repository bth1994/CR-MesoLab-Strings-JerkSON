package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        String output = (new Main()).readRawDataToString();
        // TODO: parse the data in output into items, and display to console.
        ItemParser itemParser = new ItemParser();
        ItemCounter itemCounter = new ItemCounter();

        ArrayList<String> shoppingList = itemParser.parseRawDataIntoStringArray(output);
        ArrayList<Item> itemList = new ArrayList<>();

        for(String key : shoppingList) {
            try {
                Item item = itemParser.parseStringIntoItem(key);
                itemCounter.countItems(item);
                itemList.add(item);
            } catch (ItemParseException exception) {
                itemCounter.addToExceptionCount();
            }
        }

       System.out.println(itemCounter.toString());
    }
}
