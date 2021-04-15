package dita.shafira.mate.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dita.shafira.mate.model.User;

@Database(entities = {User.class},version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
