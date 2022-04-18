package dinhphuc.com.example.expensemanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;

@Entity

public class LoaiThu {
    @PrimaryKey(autoGenerate = true)
    public int lid ;
    @ColumnInfo(name = "userid")
    public int userid= MainActivity.id;
    @ColumnInfo(name = "ten")
    public String ten ;
}
