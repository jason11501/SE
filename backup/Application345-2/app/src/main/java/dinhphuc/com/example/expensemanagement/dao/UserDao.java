package dinhphuc.com.example.expensemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.user;

@Dao
public interface UserDao {
    @Insert
    void insert (user user);
    @Delete
    void delete (user user);
    @Update
    void update (user user);
    @Query("select * from user")
    List<user> getAll();

    @Query("select * from user where username=:username")
    List<user> getUserByUsername(String username);

    @Query("select * from user where username=:username and password=:password")
    List<user> getUserByUsernameAndPassword(String username, String password);

    @Query("select * from user where id=:id")
    List<user> getUserById(int id);

}