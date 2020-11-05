import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ResistorColor {

    private final static Map<String, Integer> colorCodes = new HashMap<>() {
        {
            put("black", 0);
            put("brown", 1);
            put("red", 2);
            put("orange", 3);
            put("yellow", 4);
            put("green", 5);
            put("blue", 6);
            put("violet", 7);
            put("grey", 8);
            put("white", 9);
        }
    };

    int colorCode(String color) {
        return colorCodes.containsKey(color) == true ? colorCodes.get(color).intValue() : 0;
    }

    String[] colors() {
        String [] strA = new String[10];

        for (String key: colorCodes.keySet()) {
            strA[colorCodes.get(key)] = key;
        }
        return strA;
    }
}
