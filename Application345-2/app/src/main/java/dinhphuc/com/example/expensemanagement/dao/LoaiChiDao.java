package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;

@Dao
public interface LoaiChiDao {
    @Query("SELECT * FROM LoaiChi where userid=:userid")
    LiveData<List<LoaiChi>> finalAll(int userid) ;

    @Insert
    void insert(LoaiChi Loaichi) ;

    @Query("select * from LoaiChi where userid=:userid")
    List<LoaiChi> finalAllLoaiChi(int userid ) ;

    @Update
    void update (LoaiChi Loaichi);
    @Delete
    void delete (LoaiChi Loaichi);

}
