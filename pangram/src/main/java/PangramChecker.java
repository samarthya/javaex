public class PangramChecker {

    private String allChars = "abcdefghijklmnopqrstuvwxyz";

    public boolean isPangram(String input) {
        if (input.length() <= 0 || input.isBlank()){
            return false;
        }
        return (allChars.chars().map(x -> (char) x).filter(x -> input.toLowerCase().indexOf(x) >= 0).count() == 26);
    }

}
