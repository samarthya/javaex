import java.util.stream.IntStream;

class PrimeCalculator {

    public int nth(int nth) {
        if (nth <= 0)
            throw new IllegalArgumentException();

        // Will skip the nth - 1
        // 2 to n-1
        return IntStream.iterate(2, i -> i + 1).filter(PrimeCalculator::isPrime).skip(nth - 1).findFirst().getAsInt();
    }

    public static boolean isPrime(int x) {
        // till half of x
        return IntStream.rangeClosed(2, (int) (x/2) ).dropWhile(i -> x % i != 0).findFirst().isEmpty();
    }
}