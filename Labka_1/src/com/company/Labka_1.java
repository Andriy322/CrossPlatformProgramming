package com.company;

import java.util.Scanner;

public class Labka_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("N: ");
        int N = scanner.nextInt();

        FractionCalculator fractionCalculator = new FractionCalculator(N);
        Fraction fraction = fractionCalculator.Sum();
        System.out.printf("Result: %s/%s", fraction.getNominator(), fraction.getDenominator());
    }
}
