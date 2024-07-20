package com.temp.models.DichVu;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class DSTienTro {
    Scanner sc = new Scanner (System.in);
    static ArrayList<TienTro> bills = new ArrayList<>(); 

    public DSTienTro() {
    }
    
    public void SortTime(){
        Collections.sort(bills, (b1, b2) -> b1.getTime().compareTo(b2.getTime()));
    }
    
    public void addBill(TienTro bill){
        bill.input();
        bills.add(bill);
        System.out.println("Khoi tao hoa don thanh cong");
    }
    
    public void output() {
        if (bills.isEmpty()) {
          System.out.println("Chua co hoa don nao duoc khoi tao.");
          return;
        }

        System.out.println("\nDanh sach hoa don da khoi tao:");
        for (TienTro bill : bills) {
            System.out.println("\nNguoi thue thu " + bill.getID());
            bill.output();
        }
    }
    
    public void modify(){
        System.out.println("Nhap ID hoa don ban muon sua: ");
        int tar = sc.nextInt();
        for (TienTro bill: bills){
            if (bill.getID()== tar){
                System.out.println("Hoa don cu: ");
                bill.output();
                System.out.println("Khoi tao lai hoa don: ");
                bill.input();
                System.out.println("Chinh sua thanh cong!!!");
                return;
            }
        }
        System.out.println("Khong tim thay hoa don, ban co muon tao moi? (Y/N): ");
        char c;
        do {
            String input = sc.next();
            c = input.charAt(0);
            if (input.length() != 1){
                c = 'W';
            }
            switch (c) {
                case 'Y':
                    TienTro bill = new TienTro();
                    this.addBill(bill);
                    return;
                case 'N':
                    break;
                default:   
                    System.out.println("Khong tim thay hoa don, ban co muon tao moi? (Y/N): ");
                    break;
            }
        }while ('N' != c);
    }
}
