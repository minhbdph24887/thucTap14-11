package com.example.thuctap.Fizzbuzz;

import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number;
        while (true) {
            System.out.printf("Enter Your Number: ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                break;
            } else {
                System.out.println("Your Number An Integer");
                scanner.next();
            }
        }

        if (number % 3 == 0 && number % 5 != 0) {
            System.out.println("Number " + number + " divisible by 3");
        } else if (number % 5 == 0 && number % 3 != 0) {
            System.out.println("Number " + number + " divisible by 5");
        } else if (number % 3 == 0 && number % 5 == 0) {
            System.out.println("Number " + number + " divisible by 3 and 5");
        } else {
            System.out.println("Number " + number + " not divisible by any number");
        }

        scanner.close();
    }
}
