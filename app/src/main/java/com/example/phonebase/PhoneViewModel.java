package com.example.phonebase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*  Model task is to hold data that needs to be displayed in the activity.
    Models can last longer than activities, thus they allow to retrieve data after activity restart.
 */
public class PhoneViewModel extends AndroidViewModel {

    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllPhones;

    public PhoneViewModel(@NonNull Application application) {
        super(application);

        mRepository = new PhoneRepository(application);
        mAllPhones = mRepository.getAllPhones();
    }

    public void insert(Phone phone) {
        mRepository.insert(phone);
    }

    public void update(Phone phone) { mRepository.update(phone); }

    public LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
