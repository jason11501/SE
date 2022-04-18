package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.Thu;

@Dao
public interface ThuDao {

    @Query("select * from Thu where userid=:userid and ngay like '%'|| :month||'%'")
    List<Thu> finAllThuByMonth(int userid, String month);

    @Query("SELECT * FROM Thu where userid=:userid")
    LiveData<List<Thu>> finalAll(int userid) ;

    @Query("select sotien from Thu where userid=:userid")
    List<Float> getTien(int userid );

    @Query("select * from Thu where userid=:userid")
    List<Thu> finalAllThu(int userid ) ;
    @Insert
    void insert(Thu thu) ;

    @Update
    void update (Thu thu);
    @Delete
    void delete (Thu thu);

    @Query("Delete from Thu")
    void deleteAll();
}