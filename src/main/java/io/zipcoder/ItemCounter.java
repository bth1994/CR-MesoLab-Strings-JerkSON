package io.zipcoder;

import java.util.HashMap;
import java.util.Map;

public class ItemCounter {
    Map<String, Integer> nameCounter = new HashMap<>();
    Map<Double, Integer> milkPriceCounter = new HashMap<>();
    Map<Double, Integer> breadPriceCounter = new HashMap<>();
    Map<Double, Integer> applePriceCounter = new HashMap<>();
    Map<Double, Integer> cookiePriceCounter = new HashMap<>();

    Integer exceptionCounter = 0;

    public void countItems(Item item) {

        switch(item.getName()) {

            case "Milk":

                if(!nameCounter.containsKey("Milk")) {
                    nameCounter.put("Milk", 1);
                } else {
                    nameCounter.put("Milk", nameCounter.get("Milk") + 1);
                }

                if(!milkPriceCounter.containsKey(item.getPrice())) {
                    milkPriceCounter.put(item.getPrice(), 1);
                } else {
                    milkPriceCounter.put(item.getPrice(), milkPriceCounter.get(item.getPrice()) + 1);
                }
                break;

            case "Bread":

                if(!nameCounter.containsKey("Bread")) {
                    nameCounter.put("Bread", 1);
                } else {
                    nameCounter.put("Bread", nameCounter.get("Bread") + 1);
                }

                if(!breadPriceCounter.containsKey(item.getPrice())) {
                    breadPriceCounter.put(item.getPrice(), 1);
                } else {
                    breadPriceCounter.put(item.getPrice(), breadPriceCounter.get(item.getPrice()) + 1);
                }
                break;

            case "Apples":

                if(!nameCounter.containsKey("Apples")) {
                    nameCounter.put("Apples", 1);
                } else {
                    nameCounter.put("Apples", nameCounter.get("Apples") + 1);
                }

                if(!applePriceCounter.containsKey(item.getPrice())) {
                    applePriceCounter.put(item.getPrice(), 1);
                } else {
                    applePriceCounter.put(item.getPrice(), applePriceCounter.get(item.getPrice()) + 1);
                }
                break;

            case "Cookies":

                if(!nameCounter.containsKey("Cookies")) {
                    nameCounter.put("Cookies", 1);
                } else {
                    nameCounter.put("Cookies", nameCounter.get("Cookies") + 1);
                }

                if(!cookiePriceCounter.containsKey(item.getPrice())) {
                    cookiePriceCounter.put(item.getPrice(), 1);
                } else {
                    cookiePriceCounter.put(item.getPrice(), cookiePriceCounter.get(item.getPrice()) + 1);
                }
                break;
            default:
                break;
        }
    }

    public void addToExceptionCount() {
        exceptionCounter += 1;
    }

    public Integer getExceptionCounter() {
        return this.exceptionCounter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : nameCounter.entrySet()) {
            sb.append("\nname:\t\t" + entry.getKey() + "\t\t\tseen: " + entry.getValue() + " time(s)");
            sb.append("\n==================\t\t\t==================");

            switch(entry.getKey()) {
                case "Apples":
                    for(Map.Entry<Double, Integer> price : applePriceCounter.entrySet()) {
                        sb.append("\nPrice:\t\t" + price.getKey() + "\t\t\tseen: " + price.getValue() + " time(s)");
                        sb.append("\n------------------\t\t\t------------------");
                    }
                    break;
                case "Cookies":
                    for(Map.Entry<Double, Integer> price : cookiePriceCounter.entrySet()) {
                        sb.append("\nPrice:\t\t" + price.getKey() + "\t\t\tseen: " + price.getValue() + " time(s)");
                        sb.append("\n------------------\t\t\t------------------");
                    }
                    break;
                case "Milk":
                    for(Map.Entry<Double, Integer> price : milkPriceCounter.entrySet()) {
                        sb.append("\nPrice:\t\t" + price.getKey() + "\t\t\tseen: " + price.getValue() + " time(s)");
                        sb.append("\n------------------\t\t\t------------------");
                    }
                    break;
                case "Bread":
                    for(Map.Entry<Double, Integer> price : breadPriceCounter.entrySet()) {
                        sb.append("\nPrice:\t\t" + price.getKey() + "\t\t\tseen: " + price.getValue() + " time(s)");
                        sb.append("\n------------------\t\t\t------------------");
                    }
            }
        }
        sb.append("\nErrors\t\t\t\t\t\tseen: " + getExceptionCounter() + " time(s)");
        return sb.toString();
    }
}
