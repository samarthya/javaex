import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Rational {
    private final Logger log = Logger.getLogger(Rational.class.getName());

    private int num, den;

    Rational(int numerator, int denominator) {
        num = numerator;
        if (num == 0) {
            // No matter what is denominator
            // 0/4 = 0 = 0/1
            denominator = 1;
        } else if (num == denominator) {
            num = den = 1;
        } else {
            den = denominator;
        }

        if (den < 0) {
            num *= -1;
            den *= -1;
        }
        reduce();
    }

    private void reduce() {
        // int sign  = ( num < 0 || den < 0)? -1 : 1;
        int hcf = 0;
        log.log(Level.FINE, " In Reduce: " + num + "/" + den);

        if (Math.abs(num) > 1 && Math.abs(den) > 1) {
            hcf = BigInteger.valueOf(num).gcd(BigInteger.valueOf(den)).intValue();
            log.log(Level.INFO, " HCF: " + hcf);

            if (hcf != 0) {
                log.log(Level.WARNING, " Can be reduced!");
                den /= hcf;
                num /= hcf;
            }
        }


    }

    public Rational subtract(Rational n) {
        return Stream.of(this, n).reduce((n1, n2) -> {
            return new Rational(Math.subtractExact(
                    Math.multiplyExact(n1.num, n2.den),
                    Math.multiplyExact(n2.num, n1.den)),
                    Math.multiplyExact(n1.den, n2.den));

        }).get();
    }

    public Rational multiply(Rational n) {
        return Stream.of(this, n).reduce((n1, n2) -> {
            return new Rational(
                    Math.multiplyExact(n1.num, n2.num),
                    Math.multiplyExact(n2.den, n1.den));

        }).get();
    }


    public Rational divide(Rational n) {
        return Stream.of(this, n).reduce((n1, n2) -> {
            return new Rational(
                    Math.multiplyExact(n1.num, n2.den),
                    Math.multiplyExact(n2.num, n1.den));

        }).get();
    }

    public Rational abs() {
        return new Rational(Math.abs(num), Math.abs(den));
    }

    public Rational pow(int p) {


        if (p > 0) {
            return new Rational(
                    IntStream.range(0, p).mapToObj(x -> num).reduce((x1, x2) -> x1 * x2).get(),
                    IntStream.range(0, p).mapToObj(x -> den).reduce((x1, x2) -> x1 * x2).get()
            );
        } else if (p < 0){
            return new Rational(
                    IntStream.range(0, p).mapToObj(x -> den).reduce((x1, x2) -> x1 * x2).get(),
                    IntStream.range(0, p).mapToObj(x -> num).reduce((x1, x2) -> x1 * x2).get()
            );
        }

        return new Rational(1, 1);
    }

    public double exp(double p) {
        return Math.pow(Math.pow(p, (1.0/den)), num);
    }

    public Rational add(Rational n) {
        return Stream.of(this, n).reduce((n1, n2) -> {
            return new Rational(Math.addExact(
                    Math.multiplyExact(n1.num, n2.den),
                    Math.multiplyExact(n2.num, n1.den)),
                    Math.multiplyExact(n1.den, n2.den));

        }).get();
    }

    int getNumerator() {
        return num;
    }

    int getDenominator() {
        return den;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Rational other = (Rational) obj;
        return this.getNumerator() == other.getNumerator()
                && this.getDenominator() == other.getDenominator();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.getNumerator();
        result = prime * result + this.getDenominator();

        return result;
    }
}
