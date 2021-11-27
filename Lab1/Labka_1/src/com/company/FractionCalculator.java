package com.company;

import java.math.BigInteger;

public class FractionCalculator
{

    private final int N;
    private long nominatorSmallLong = 0;
    private long denominatorSmallLong = 0;
    private BigInteger nominatorBigInteger = BigInteger.ZERO;
    private BigInteger denominatorBigInteger = BigInteger.ZERO;

    public FractionCalculator (int n)
    {
        N = n;
    }
    //1  / (n ^ 2 + 7 * n)

    public Fraction Sum()
    {
        if (N > 15)
        {
            return BigNumberCalc();
        }
        else
        {
            return SmallNumberCalc();
        }
    }

    private Fraction BigNumberCalc()
    {
        BigInteger currentNominator = BigInteger.ONE;
        BigInteger currentDenominator = BigInteger.valueOf(1).
                add(BigInteger.valueOf(7L));
        nominatorBigInteger = currentNominator;
        denominatorBigInteger = currentDenominator;

        for (int i = 2; i <= N; ++i)
        {
            currentNominator = BigInteger.ONE;
            currentDenominator = BigInteger.valueOf(i).pow(2).
                    add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(7L)));
            BigInteger leastCommonMultiple = CommonMultiple(denominatorBigInteger, currentDenominator);
            BigInteger multiplier = leastCommonMultiple.divide(denominatorBigInteger);
            nominatorBigInteger = nominatorBigInteger.multiply(multiplier);
            denominatorBigInteger = denominatorBigInteger.multiply(multiplier);
            multiplier = leastCommonMultiple.divide(currentDenominator);
            currentNominator = currentNominator.multiply(multiplier);
            nominatorBigInteger = nominatorBigInteger.add(currentNominator);

            reduceBigIntegerFraction();
        }
        Fraction fraction = new Fraction();
        fraction.setNominator(nominatorBigInteger);
        fraction.setDenominator(denominatorBigInteger);
        return fraction;
    }

    private Fraction SmallNumberCalc()
    {
        long currentNominator = 1;
        long currentDenominator = 1 + 7;

        nominatorSmallLong = currentNominator;
        denominatorSmallLong = currentDenominator;

        for (int i = 2; i <= N; ++i)
        {
            currentNominator = 1;
            currentDenominator = 1 * i * i + 7 * i;


            long leastCommonMultiple = CommonMultiple(denominatorSmallLong, currentDenominator);
            long multiplier = leastCommonMultiple / denominatorSmallLong;
            nominatorSmallLong *= multiplier;
            denominatorSmallLong *= multiplier;
            multiplier = leastCommonMultiple / currentDenominator;
            currentNominator *= multiplier;
            nominatorSmallLong += currentNominator;

            reduceSmallLongFraction();
        }
        Fraction fraction = new Fraction();
        fraction.setNominator(nominatorSmallLong);
        fraction.setDenominator(denominatorSmallLong);
        return fraction;
    }

    private long GreatestDivisible(long nominator, long denominator)
    {
        return denominator == 0 ? nominator : GreatestDivisible(denominator, nominator % denominator);
    }

    private long CommonMultiple(long first, long second)
    {
        return (first * second) / GreatestDivisible(first, second);
    }

    private BigInteger CommonMultiple(BigInteger first, BigInteger secondDenominator)
    {
        return first.multiply(secondDenominator).divide(first.gcd(secondDenominator));
    }

    private void reduceSmallLongFraction()
    {
        long divider = GreatestDivisible(nominatorSmallLong, denominatorSmallLong);
        if (divider != 0)
        {
            nominatorSmallLong /= divider;
            denominatorSmallLong /= divider;
        }
    }

    private void reduceBigIntegerFraction()
    {
        BigInteger divider = nominatorBigInteger.gcd(denominatorBigInteger);
        if (!divider.equals(BigInteger.ZERO))
        {
            nominatorBigInteger = nominatorBigInteger.divide(divider);
            denominatorBigInteger = denominatorBigInteger.divide(divider);
        }
    }
}
