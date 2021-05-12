package com.example.phonebase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/*  Repository is a single data source for the application. It Implements logic that decides where
    the data should be retrieved from. It uses RoomDatabase class and DAO.
 */
public class PhoneRepository {

    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;

    PhoneRepository(Application application) {

        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);
        mPhoneDao = phoneRoomDatabase.phoneDao();
        mAllPhones = mPhoneDao.getAlphabetizedElements();
    }

    void insert(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.insert(phone);
        });
    }

    void update(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.update(phone);
        });
    }

    LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deleteAll();
        });
    }
}
