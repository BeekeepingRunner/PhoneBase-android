package com.example.phonebase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*  DAO (Data Access Object) - interface maps sql queries to methods that enable reading/writing
    from/to base. Class that implements those methods is created automatically by room library.
 */
@Dao
public interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Update(onConflict = OnConflictStrategy.ABORT)
    void update(Phone phone);

    @Query("DELETE FROM phone")
    void deleteAll();

    // LiveData container enables listening to data changes.
    // It notifies watcher in the main application thread
    @Query("SELECT * FROM phone ORDER BY model ASC")
    LiveData<List<Phone>> getAlphabetizedElements();
}
