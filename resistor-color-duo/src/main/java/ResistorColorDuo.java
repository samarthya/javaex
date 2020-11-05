import java.util.Arrays;

class ResistorColorDuo {
    // Define the colors list
    private final static String[] colorList = new String[] { "black", "brown", "red", "orange", "yellow", "green",
            "blue", "violet", "grey", "white" };

    int colorCode(String color) {
        return Arrays.asList(colorList).indexOf(color);
    }

    String[] colors() {
        return colorList;
    }

    int value(String[] colors) {
        if (colors.length < 2) {
            return Integer.parseInt(String.format("%d", colorCode(colors[0])));
        }
        return Integer.parseInt(String.format("%d%d", colorCode(colors[0]),colorCode(colors[1])));
    }
}
