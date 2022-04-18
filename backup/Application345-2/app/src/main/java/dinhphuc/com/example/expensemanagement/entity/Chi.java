package dinhphuc.com.example.expensemanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;

@Entity

public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int tid ;
    @ColumnInfo(name = "userid")
    public int userid= MainActivity.id;
    @ColumnInfo(name = "ltid")
    public int ltid ;
    @ColumnInfo(name = "ten")
    public String ten ;
    @ColumnInfo(name = "sotien")
    public float sotien ;
    @ColumnInfo(name = "ghichu")
    public String ghichu ;
    @ColumnInfo(name = "ngay")
    public String ngay ;

    public int getTid() {
        return tid;
    }

    public int getLtid() {
        return ltid;
    }

    public String getTen() {
        return ten;
    }

    public float getSotien() {
        return sotien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setLtid(int ltid) {
        this.ltid = ltid;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSotien(float sotien) {
        this.sotien = sotien;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
