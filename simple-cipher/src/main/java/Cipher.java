import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {
    private static final Logger logger = Logger.getLogger(Cipher.class.getName());

    private final String keyLib = "abcdefghijklmnopqrstuvwxyz";
    private String key;

    // Generate random key.
    private String genRandomKey() {
        Random rnd = new Random();
        return IntStream.range(0, 100).
                mapToObj(i -> keyLib.charAt(rnd.nextInt(keyLib.length()))).
                map(m -> String.valueOf(m)).
                collect(Collectors.joining());
    }

    public Cipher(String key) {
        if (key == null) {
            this.key = genRandomKey();
        } else {
            this.key = key;
        }
    }

    public Cipher() {
        // No key means generate a random Key
        this(null);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    private String encodeChar(int c, int k) {
        // The position of Key in the alphabets seq - Determines the Shift number
        int indexOfKey = keyLib.indexOf(k);

        // The position of the Char to shift.
        int indexOfPlain = keyLib.indexOf(c);

        // Move the Plain Char to the new Encoded Char
        int encodedScript = (indexOfPlain + indexOfKey)%keyLib.length();

        logger.log(Level.INFO, String.format(" Index K %d, C %d (%s), E %d (%s)", indexOfKey, indexOfPlain, String.valueOf(keyLib.charAt(indexOfPlain)), encodedScript, String.valueOf(keyLib.charAt(encodedScript))));

        return String.valueOf(keyLib.charAt(encodedScript));
    }


    public String encodev1(String plainText) {
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }

        return IntStream.
                range(0, plainText.length()).
                mapToObj(index ->
                     encodeChar(
                            plainText.charAt(index),
                            getKeyChar(index))
                ).collect(Collectors.joining());

    }

    private String decodeChar(int c, int k) {
        // Position to adjust - Delta
        int indexOfKey = keyLib.indexOf(k);

        //The location of the encoded char in Alphabets
        int indexOfEncrypt = keyLib.indexOf(c);

        // The plain text
        int plainScript = (indexOfEncrypt - indexOfKey);

        // If index is less than 0, need to loop back.
        plainScript = (plainScript < 0) ? keyLib.length() + plainScript: plainScript;

        logger.log(Level.INFO, String.format(" Index K %d, C %d (%s), E %d (%s)", indexOfKey, indexOfEncrypt, String.valueOf(keyLib.charAt(indexOfEncrypt)), plainScript, String.valueOf(keyLib.charAt(plainScript))));
        return String.valueOf(keyLib.charAt(plainScript));
    }

    public String decodev1(String plainText) {
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }


        return IntStream.
                range(0, plainText.length()).
                mapToObj(index -> decodeChar(
                            plainText.charAt(index),
                            getKeyChar(index))
                ).collect(Collectors.joining());

    }

    /**
     * Find the key character that determines how much to shift?
     * @param index
     * @return
     */
    public int getKeyChar(int index) {
        return (index<this.key.length())?this.key.charAt(index):this.key.charAt(index%this.key.length());
    }


    public String encode(String plainText) {
        return encodev1(plainText);
    }

    public String decode(String encodedText) {
        return decodev1(encodedText);
    }
}