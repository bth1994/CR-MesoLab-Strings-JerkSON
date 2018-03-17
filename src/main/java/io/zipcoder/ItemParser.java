package io.zipcoder;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    Map<String, String> itemAttributes;

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public List<String[]> createArrayOfAttribute(ArrayList<String> lessRawData) {
        List<String[]> itemInfo = new ArrayList<>();
        for(int i = 0; i < lessRawData.size(); i ++) {
            itemInfo.add((findKeyValuePairsInRawItemData(lessRawData.get(i))).toArray(new String[4]));
        }
        return itemInfo;
    }

    public void passArrayIntoParser(List<String[]> list) throws ItemParseException{
        for(String[] key : list) {

            String[] name = splitIntoKeyAndValue("[n][a][m][e]",key[0]);
            String[] price = splitIntoKeyAndValue("[p][r][i][c][e]", key[1]);
            String[] type = splitIntoKeyAndValue("[t][y][p][e]", key[2]);
            String[] expiration = splitIntoKeyAndValue("[e][x][p][i][r][a][t][i][o][n]", key[3]);

            addtoMap(name);
            addtoMap(price);
            addtoMap(type);
            addtoMap(expiration);
        }
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{

        try {
            String itemName =

        } catch (ItemParseException exception) {
            throw exception;
        }


        findKeyValuePairsInRawItemData(rawItem);
        return null;
    }

    public void addtoMap(String[] array) {
        itemAttributes.put(array[0], array[1]);
    }

    public String[] splitIntoKeyAndValue(String attribute, String rawItem) throws ItemParseException{
        Pattern pattern = Pattern.compile(attribute, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(rawItem);
        String[] keyValue;

        if(matcher.find()) {
             keyValue = matcher.group(0).split(":");

        } else {
            throw new ItemParseException();
        }
        return keyValue;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^|@|%|!]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);

        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString) {
        return new ArrayList<>(Arrays.asList(inputString.split(stringPattern)));
    }

}
