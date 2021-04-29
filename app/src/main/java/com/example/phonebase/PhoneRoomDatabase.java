package com.example.phonebase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Abstract class (room lib. auto-generates implementation) representing data base.
   Enables:
   - access to the DAO
   - access to the reference to the single instance of db object
   - event handling (e.g. creating, opening DB, migrating it to the newer versions)
 */
// exportSchema = false - eliminates warnings while building DB
@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {

    public abstract PhoneDao phoneDao();

    // Singleton implementation
    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (PhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        PhoneRoomDatabase.class, "phonebase")
                                        .addCallback(sRoomDatabaseCallback)
                                        .fallbackToDestructiveMigration()
                                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // handles callback associated with DB events (e.g. onCreate, onOpen)
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        // First DB creation
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                PhoneDao dao = INSTANCE.phoneDao();

                dao.insert(new Phone("Nokia", "3310", "none", "https://www.nokia.com/phones/en_int/nokia-3310"));
                dao.insert(new Phone("Samsung", "Galaxy S6", "Lollipop", "https://www.samsung.com/global/galaxy/galaxys6/galaxy-s6/"));
                dao.insert(new Phone("Samsung", "Galaxy S10", "9.0 Pie", "https://www.samsung.com/global/galaxy/galaxy-s10/"));
                dao.insert(new Phone("Huawei", "P20", "8.1", "https://consumer.huawei.com/en/phones/"));

                // ...?
            });
        }
    };
}
