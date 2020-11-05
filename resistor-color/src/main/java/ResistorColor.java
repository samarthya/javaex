import java.util.*;

class ResistorColor {

    // Define the colors list
    private final static String[] colorList = new String[] { "black", "brown", "red", "orange", "yellow", "green",
            "blue", "violet", "grey", "white" };

    int colorCode(String color) {
        return Arrays.asList(colorList).indexOf(color);
    }

    String[] colors() {
        return colorList;
    }
}
