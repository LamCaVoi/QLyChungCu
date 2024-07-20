package com.temp.models.NguoiThue;

import com.temp.Services.Services;
import java.util.Scanner;

public class NguoiThue {

    Scanner sc = new Scanner(System.in);
    private int id;
    private int idPhg;
    private String ten;
    private String sdt;
    private String ngaySinh;
    private String queQuan;
    private String cccd;

    public NguoiThue() {
    }

    public NguoiThue(String ten, String sdt, String ngaySinh, String queQuan, String cccd, int idPhg) {
        this.ten = ten;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.cccd = cccd;
        this.idPhg = idPhg;
    }
    

    public int getIdPhg() {
        return idPhg;
    }
    
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPhg(int idPhg) {
        this.idPhg = idPhg;
    }
    
    public int getId() {
        return id;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    
    

    @Override
    public String toString() {

        return String.format("""
            ID: %d
            Tên: %s
            Số điện thoại: %s
            Ngày sinh: %s
            Quê quán: %s
            Số CCCD: %s
            """, id, ten, sdt, ngaySinh, queQuan, cccd);
    }

    public void input() {
        System.out.println("Nhap ho va ten");
        sc.next();
        ten = sc.nextLine();
        System.out.println("Nhap so dien thoai");
        sdt = sc.nextLine();
        System.out.println("Nhap ngay sinh (dd/MM/yyyy)");
        ngaySinh = sc.nextLine();
        System.out.println("Nhap que quan");
        queQuan = sc.nextLine();
        System.out.println("Nhap can cuoc cong dan");
        cccd = sc.nextLine();
    }

    public void output() {
        System.out.println("ID cua ban la " + id);
        System.out.println("Ho va ten cua ban la " + ten);
        System.out.println("So dien thoai cua ban la " + sdt);
        System.out.println("Ngay sinh cua ban la " + ngaySinh);
        System.out.println("Que quan cua ban la " + queQuan);
        System.out.println("Can cuoc cong dan cua ban la " + cccd);
    }

}
