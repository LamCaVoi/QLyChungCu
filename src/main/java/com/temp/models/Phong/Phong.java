package com.temp.models.Phong;


import java.util.Scanner;

public class Phong {
    Scanner sc = new Scanner(System.in);
    private int ID;
    private static int currID = 0;
    double dienTich;
    long giaTien;
    String trangThai;

    public Phong() {
        this.ID = ++currID;
    }

    public Phong(double dienTich, long giaTien, String trangThai) {
        this.ID = ++currID;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }

    public Phong(double dienTich, long giaTien) {
        this.dienTich = dienTich;
        this.giaTien = giaTien;
    }

    public void input(){
        System.out.println("Nhap dien tich: ");
        this.dienTich = sc.nextDouble();
        System.out.println("Nhap gia tien/m2: ");
        this.giaTien = sc.nextLong();
        System.out.println("Nhap trang thai(DA_DAT_COC, TRONG, DANG_DUOC_SU_DUNG):");
        this.trangThai = sc.next();
    }
    
    public void output(){
        System.out.println("\nID: " + ID);
        System.out.println("\nDien tich da nhap: " + dienTich + " m2");
        System.out.println("\nGia tien da nhap: " + giaTien + " VND/m2");
        System.out.println("\nTrang thai: " + trangThai);
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Phong{" + "ID=" + ID + ", dienTich=" + dienTich + ", giaTien=" + giaTien + ", trangThai=" + trangThai + '}';
    }

    
    
    
}
