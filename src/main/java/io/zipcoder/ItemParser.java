package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {

            String name = parseName(rawItem);
            String priceAsString = parsePrice(rawItem);
            Double price = Double.parseDouble(priceAsString);
            String type = parseType(rawItem);
            String expiration = parseExpiration(rawItem);

            return new Item(name, price, type, expiration);
    }

    public String parseName(String rawItem) throws ItemParseException {
        Pattern[] name = new Pattern[4];

        name[0] = Pattern.compile("(?i)([n][a][m][e]:)([m][i][l][k])");
        name[1] = Pattern.compile("(?i)([n][a][m][e]:)([b][r][e][a][d])");
        name[2] = Pattern.compile("(?i)([n][a][m][e]:)([a][p][p][l][e][s])");
        name[3] = Pattern.compile("(?i)([n][a][m][e]:)([c][o0][o0][k][i][e][s]?)");

        for (int i = 0; i < name.length; i++) {
            Matcher matcher = name[i].matcher(rawItem);
            if (matcher.find()) {
                switch (i) {
                    case 0:
                        return "Milk";
                    case 1:
                        return "Bread";
                    case 2:
                        return "Apples";
                    case 3:
                        return "Cookies";
                }
            }
        }
        throw new ItemParseException();
    }

    public String parsePrice(String rawItem) throws ItemParseException {
        Pattern price = Pattern.compile("((?i)[p][r][i][c][e]:)(\\d\\d?.\\d\\d)");
        Matcher matcher = price.matcher(rawItem);

        if(matcher.find()) {
            return matcher.group(2);
        } else {
            throw new ItemParseException();
        }
    }

    public String parseType(String rawItem) throws ItemParseException {
        Pattern type = Pattern.compile("(?i)([t][y][p][e]:)([f][o][o][d])");
        Matcher matcher = type.matcher(rawItem);

        if(matcher.find()) {
            return matcher.group(2);
        } else {
            throw new ItemParseException();
        }
    }

    public String parseExpiration(String rawItem) throws ItemParseException {
        Pattern expiration = Pattern.compile("(?i)([e][x][p][i][r][a][t][i][o][n]:)([\\d][\\d]?.\\d\\d?.\\d\\d\\d?\\d?)");
        Matcher matcher = expiration.matcher(rawItem);

        if(matcher.find()) {
            return matcher.group(2);
        } else {
            throw new ItemParseException();
        }
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
