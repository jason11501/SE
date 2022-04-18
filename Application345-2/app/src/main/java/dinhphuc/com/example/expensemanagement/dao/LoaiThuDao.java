package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;

@Dao
public interface LoaiThuDao {
    @Query("SELECT * FROM loaithu where userid=:userid")
    LiveData<List<LoaiThu>> finalAll(int userid ) ;

    @Query("select * from LoaiThu where userid=:userid")
    List<LoaiThu> finalAllLoaiThu(int userid ) ;

    @Insert
    void insert(LoaiThu Loaithu) ;

    @Update
    void update (LoaiThu Loaithu);

    @Delete
    void delete (LoaiThu Loaithu);

}