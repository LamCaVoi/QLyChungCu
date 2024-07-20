
package com.temp.models.DichVu;

import java.util.Scanner;

public class DichVu {
    static Scanner sc = new Scanner(System.in);
    private static int water = 16000; 
    private static int elec = 3000;
    private static int internet = 50000;
    private static int others = 100000;
    private static int parking = 25000;
    private static int mantain = 50000;

    public DichVu() {
    }
    
    public static void input(){
        System.out.println("Nhap tien cho 1 khoi nuoc (VND): ");
        water = sc.nextInt();
        System.out.println("Nhap tien cho 1 ky dien (VND): ");
        elec = sc.nextInt();
        System.out.println("Nhap tien Internet 1 thang (VND): ");
        internet = sc.nextInt();
        System.out.println("Nhap tien cho cac dich vu chung cu 1 thang (VND): ");
        others = sc.nextInt();
        System.out.println("Nhap phi gui xe (VND): ");
        parking = sc.nextInt();
        System.out.println("Nhap phi bao duong chung cu (VND): ");
        mantain = sc.nextInt();
    }
    
    public static void output(){
        System.out.println("\nBang gia cho cac dich vu trong chung cu:");
        System.out.println("Tien cho 1 khoi nuoc: " + water + " VND");
        System.out.println("Tien cho 1 ky dien: " + elec + " VND");
        System.out.println("Tien Internet: " + internet + " VND");
        System.out.println("Tien cho cac dich vu khac: " + others + " VND");
        System.out.println("Phi gui xe: " + parking + " VND");
        System.out.println("Phi bao duong chung cu: " + mantain + " VND");   
    }
    
    public static int getWater() {
        return water;
    }

    public static void setWater(int water) {
        DichVu.water = water;
    }

    public static int getElec() {
        return elec;
    }

    public static void setElec(int elec) {
        DichVu.elec = elec;
    }

    public static int getInternet() {
        return internet;
    }

    public static void setInternet(int internet) {
        DichVu.internet = internet;
    }

    public static int getOthers() {
        return others;
    }

    public static void setOthers(int others) {
        DichVu.others = others;
    }

    public static int getParking() {
        return parking;
    }

    public static void setParking(int parking) {
        DichVu.parking = parking;
    }

    public static int getMantain() {
        return mantain;
    }

    public static void setMantain(int mantain) {
        DichVu.mantain = mantain;
    }    
}
