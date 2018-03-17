package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemCounterTest {

    ItemParser itemParser;
    ItemCounter itemCounter;
    Item item;
    Item item2;

    @Before
    public void setup() throws ItemParseException {
        itemParser = new ItemParser();
        itemCounter = new ItemCounter();
        item = new Item("Cookies", 2.25, "Food", "3/22/2016");
        item2 = new Item("Cookies", 1.23, "Food", "3/22/2016");
    }

    @Test
    public void countCookiesCountTest() {
        itemCounter.countItems(item);
        itemCounter.countItems(item2);

        Integer expected = 2;
        Integer actual = itemCounter.nameCounter.get("Cookies");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addToExceptionCountTest() {
        itemCounter.addToExceptionCount();
        itemCounter.addToExceptionCount();

        Integer expected = 2;
        Integer actual = itemCounter.getExceptionCounter();

        Assert.assertEquals(expected, actual);
    }



}
