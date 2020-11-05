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
        int totalValue = 0;

        for (String c: colors) {

            if (totalValue == 0) {
                totalValue += colorCode(c);
            } else if (totalValue < 10){
                totalValue *= 10;
                totalValue += colorCode(c);
            } else {
                // Ignore the third color
                break;
            }
        }

        return totalValue;
    }
}
