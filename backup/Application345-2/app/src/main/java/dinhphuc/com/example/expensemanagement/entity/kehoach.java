package dinhphuc.com.example.expensemanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;

@Entity(tableName = "kehoach")

public class kehoach {
    @PrimaryKey(autoGenerate = true)
    public int id ;
    @ColumnInfo(name = "userid")
    public int userid= MainActivity.id;
    @ColumnInfo(name = "thang")
    public int thang ;
    @ColumnInfo(name = "nam")
    public int nam ;
    @ColumnInfo(name = "tongthu")
    public Float tongthu;
    @ColumnInfo(name = "tongchi")
    public Float tongchi;

    public kehoach(int thang, int nam, Float tongthu, Float tongchi) {
        this.thang = thang;
        this.nam = nam;
        this.tongthu = tongthu;
        this.tongchi = tongchi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public Float getTongthu() {
        return tongthu;
    }

    public void setTongthu(Float tongthu) {
        this.tongthu = tongthu;
    }

    public Float getTongchi() {
        return tongchi;
    }

    public void setTongchi(Float tongchi) {
        this.tongchi = tongchi;
    }
}
