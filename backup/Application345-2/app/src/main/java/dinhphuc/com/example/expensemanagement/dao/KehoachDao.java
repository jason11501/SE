package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.kehoach;

@Dao
public interface KehoachDao {
    @Query ("select * from kehoach where userid=:userid")
    List<kehoach> getAll( int userid );

    @Query ("select * from kehoach where (nam = :nam and thang=:thang and userid=:userid)")
    List<kehoach> CheckExists(int thang,int nam, int userid );

    @Insert
    void insert(kehoach kehoach) ;

    @Update
    void update (kehoach kehoach);
    @Delete
    void delete (kehoach kehoach);

    @Query("Delete from kehoach")
    void deleteAll();

}