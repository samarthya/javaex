
class ReverseString {

    String reverse(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return inputString;
        }

        StringBuilder sb = new StringBuilder(inputString).reverse();
        return sb.toString();
    }

}
