package com.example.thuctap.tim_so_lon_nhat_trong_cac_so;

import java.util.Scanner;

public class MainJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        while (true) {
            n = readIntNumber(scanner, "Please enter the number of elements: ");
            if (n > 0) {
                break;
            } else {
                System.out.println("The number of elements must be greater than 0");
            }
        }


        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = readIntNumber(scanner, "Please enter the number " + (i + 1) + ":");
        }

        int maxNumber = numbers[0];
        int secondNumber = maxNumber;
        for (int i = 1; i < n; i++) {
            if (numbers[i] > maxNumber) {
                secondNumber = maxNumber;
                maxNumber = numbers[i];
            } else if (numbers[i] > secondNumber && numbers[i] != maxNumber) {
                secondNumber = numbers[i];
            }
        }

        if (secondNumber == maxNumber) {
            System.out.println("The 2nd largest number was not found");
        } else {
            System.out.println("2nd largest number " + secondNumber);
        }

        scanner.close();
    }

    static int readIntNumber(Scanner scanner, String intro) {
        int n;
        while (true) {
            System.out.printf(intro);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter an integer");
                scanner.next();
            }
        }

        return n;
    }

}
