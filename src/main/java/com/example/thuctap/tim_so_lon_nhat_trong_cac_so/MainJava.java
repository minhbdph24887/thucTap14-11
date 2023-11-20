package com.example.thuctap.tim_so_lon_nhat_trong_cac_so;

import java.util.Scanner;

public class MainJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        while (true) {
            System.out.printf("Please enter the number of elements: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    break;
                } else {
                    System.out.println("The number of elements must be greater than 0");
                }
            } else {
                System.out.println("Please enter an Integer");
                scanner.next();
            }
        }


        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.printf("Please enter the number " + (i + 1) + ":");
                if (scanner.hasNextInt()) {
                    numbers[i] = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Please enter an integer");
                    scanner.next();
                }
            }
        }

        int maxNumber = Integer.MIN_VALUE;
        int secondNumber = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (numbers[i] > maxNumber) {
                secondNumber = maxNumber;
                maxNumber = numbers[i];
            } else if (numbers[i] > secondNumber && numbers[i] != maxNumber) {
                secondNumber = numbers[i];
            }
        }

        if (secondNumber == Integer.MIN_VALUE) {
            System.out.println("The 2nd largest number was not found");
        } else {
            System.out.println("2nd largest number " + secondNumber);
        }

        scanner.close();
    }
}
