package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemParser {


    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        System.out.println(response);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{
        findKeyValuePairsInRawItemData(rawItem);

        return null;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        System.out.println(response);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }



}
