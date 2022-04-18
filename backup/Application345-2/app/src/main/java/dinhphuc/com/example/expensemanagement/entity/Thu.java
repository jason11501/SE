package dinhphuc.com.example.expensemanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;

@Entity

public class Thu {
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
}
