package com.temp.models.DichVu;

import java.util.Scanner;

public final class TienTro {

    static Scanner sc = new Scanner(System.in);
    static int currID = 0;
    private int ID;
    private int IDPhg;
    private int discount;
    private String time;
    private int soDien;
    private int soNuoc;
    private long serviceFee;
    private long total;

    public void setServiceFee() {
        this.serviceFee = DichVu.getWater() * soNuoc + DichVu.getElec() * soDien + DichVu.getInternet() + DichVu.getOthers() + DichVu.getMantain() + DichVu.getParking();
        this.total = ((this.total + this.serviceFee)/100)*(100 - discount);
    }

    public TienTro() {
        this.ID = ++currID;
        discount = 0;
    }

    public TienTro(int idPhg, int discount, String time, int soDien, int soNuoc, long roomRent) {
        this.IDPhg = idPhg;
        this.ID = ++currID;
        this.discount = discount;
        this.time = time;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.total = roomRent;
        setServiceFee();
    }

    public int getIDPhg() {
        return IDPhg;
    }

    public void setIDPhg(int IDPhg) {
        this.IDPhg = IDPhg;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void outputService() {
        DichVu.output();
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        setServiceFee();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
        setServiceFee();
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
        setServiceFee();
    }

    public void input() {
        System.out.println("\nKhoi tao 1 hoa don tien nha: ");
        System.out.println("Nhap so nuoc: ");
        this.soNuoc = sc.nextInt();
        System.out.println("Nhap so dien: ");
        this.soDien = sc.nextInt();
        System.out.println("Nhap discount (%, neu khong co nhap 0): ");
        this.discount = sc.nextInt();
        setServiceFee();
    }

    public void output() {
        System.out.println("\nHoa don thu " + this.ID + " : ");
        System.out.println("ID: " + ID);
        System.out.println("So nuoc: " + soNuoc + " m3");
        System.out.println("So dien: " + soDien + " kWh");
        System.out.println("Discount: " + discount + " %");
        System.out.println("Tien dich vu: " + serviceFee + " VND");

    }

    @Override
    public String toString() {
        return "TienTro{" + "ID=" + ID + ", discount=" + discount + ", time=" + time + ", soDien=" + soDien + ", soNuoc=" + soNuoc + ", serviceFee=" + serviceFee + '}';
    }

    public long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
    

}
