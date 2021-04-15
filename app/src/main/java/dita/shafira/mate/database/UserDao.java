package dita.shafira.mate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dita.shafira.mate.model.User;

@Dao
public interface UserDao {
    @Insert
    void login(User user);

    @Query("DELETE FROM User")
    public void nukeTable();

    @Query("SELECT * FROM User")
    List<User> user();
}
