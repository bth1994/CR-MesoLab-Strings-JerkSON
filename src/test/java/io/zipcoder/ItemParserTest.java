package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String rawSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem =    "naMe:;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
                                      +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
                                      +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp(){
        itemParser = new ItemParser();
    }

    @Test
    public void parseRawDataIntoStringArrayTest(){
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        assertEquals(expectedArraySize, actualArraySize);
    }

    @Test
    public void parseStringIntoItemTest() throws ItemParseException{
        Item expected = new Item("Milk", 3.23, "Food","1/25/2016");
        Item actual = itemParser.parseStringIntoItem(rawSingleItem);
        System.out.println(expected.toString());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ItemParseException.class)
    public void parseBrokenStringIntoItemTest() throws ItemParseException{
        itemParser.parseStringIntoItem(rawBrokenSingleItem);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTest(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).size();
        assertEquals(expected, actual);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTestIrregular(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItemIrregularSeperatorSample).size();
        assertEquals(expected, actual);
    }

    @Test
    public void parseNameMilkTest() throws ItemParseException {
        String expected = "Milk";
        String actual = itemParser.parseName(rawSingleItem);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseNameBreadTest() throws ItemParseException {
        String expected = "Bread";
        String actual = itemParser.parseName("NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseNameApplesTest() throws ItemParseException {
        String expected = "Apples";
        String actual = itemParser.parseName("naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseNameCookiesTest() throws ItemParseException {
        String expected = "Cookies";
        String actual = itemParser.parseName("naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parsePriceTest() throws ItemParseException {
        String expected = "2.25";
        String actual = itemParser.parsePrice("naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseTypeTest() throws ItemParseException {
        String expected = "Food";
        String actual = itemParser.parseType("naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseExpirationTest() throws ItemParseException {
        String expected = "3/22/2016";
        String actual = itemParser.parseExpiration("naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016");
        Assert.assertEquals(expected, actual);
    }

}
