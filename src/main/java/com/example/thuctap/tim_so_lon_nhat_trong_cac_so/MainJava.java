package com.example.thuctap.tim_so_lon_nhat_trong_cac_so;

import java.util.Scanner;

public class MainJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // khai bao mang gom 5 phan tu
        int[] numbers = new int[5];

        System.out.println("Nhap Vao Day 5 So");
        for (int i = 0; i < 5; i++) {
            numbers[i] = scanner.nextInt();
        }

        // lay so lon nhat
        int maxNumber = Integer.MIN_VALUE;
        // lay so thu hai
        int secondMax = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) {
            if (numbers[i] > maxNumber) {
                secondMax = maxNumber;
                maxNumber = numbers[i];
            } else if (numbers[i] > secondMax && numbers[i] != maxNumber) {
                secondMax = numbers[i];
            }
        }

        if (secondMax == Integer.MIN_VALUE) {
            System.out.println("Khong Co So Nao Lon Hon Thu 2");
        } else {
            System.out.println("So Lon Thu 2 La: " + secondMax);
        }
        scanner.close();
    }
}
