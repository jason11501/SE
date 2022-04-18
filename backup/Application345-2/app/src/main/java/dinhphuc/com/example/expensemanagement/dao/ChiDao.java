package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.entity.Chi;

@Dao
public interface ChiDao {

    @Query("select * from Chi where userid=:userid and ngay like '%'|| :month||'%'")
    List<Chi> finAllChiByMonth(int userid, String month);

    @Query("SELECT * FROM Chi where userid=:userid")
    LiveData<List<Chi>> finalAll(int userid) ;

    @Query("SELECT sotien from Chi where userid=:userid")
    List<Float> getTien(int userid);

    @Query("select * from Chi where userid=:userid")
    List<Chi> finalAllChi(int userid ) ;

    @Insert
    void insert(Chi Chi) ;

    @Update
    void update (Chi Chi);
    @Delete
    Void delete (Chi Chi);
    @Query("Delete from Chi")
    void deleteAll();

}