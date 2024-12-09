// 13.15
import java.math.BigInteger;
import java.util.Scanner;

public class Rational {
    private BigInteger numerator;
    private BigInteger denominator;

    public Rational(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd).multiply(denominator.signum() == -1 ? BigInteger.valueOf(-1) : BigInteger.ONE);
        this.denominator = denominator.abs().divide(gcd);
    }

    public Rational add(Rational other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator).add(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new Rational(newNumerator, newDenominator);
    }

    public Rational subtract(Rational other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator).subtract(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new Rational(newNumerator, newDenominator);
    }

    public Rational multiply(Rational other) {
        return new Rational(this.numerator.multiply(other.numerator), this.denominator.multiply(other.denominator));
    }

    public Rational divide(Rational other) {
        return new Rational(this.numerator.multiply(other.denominator), this.denominator.multiply(other.numerator));
    }

    public double toDecimal() {
        return new BigInteger(this.numerator.toString()).doubleValue() / new BigInteger(this.denominator.toString()).doubleValue();
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first rational number (numerator denominator): ");
        BigInteger numerator1 = scanner.nextBigInteger();
        BigInteger denominator1 = scanner.nextBigInteger();

        System.out.print("Enter the second rational number (numerator denominator): ");
        BigInteger numerator2 = scanner.nextBigInteger();
        BigInteger denominator2 = scanner.nextBigInteger();

        Rational r1 = new Rational(numerator1, denominator1);
        Rational r2 = new Rational(numerator2, denominator2);

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.toDecimal());
    }
}