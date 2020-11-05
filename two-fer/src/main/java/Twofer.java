public class Twofer {
    private final static String TEMPLATE = "One for %s, one for me.";

    public String twofer(String name) {
        name = (name != null) ? name : "you";
        return String.format(TEMPLATE, name);
    }
}
