//third thing in 'room database'
package com.example.wordlibirarey;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = words.class, version = 1)
public abstract class wordRoomDb extends RoomDatabase {
    //we make this constructor instance to make all app use just one copy of database
    private static wordRoomDb instance;
    // abstract that mean write method without body
    public abstract wordsDao wordsDao();
    //singleton : that make you use one instance for database
    //synchronized mean that only one thread can access and process this DB
    public static synchronized wordRoomDb getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), wordRoomDb.class
                    , "wordDB").
                    fallbackToDestructiveMigration().addCallback(callback).build();

        }
        return instance;
    }
    // make callback for room database
    public static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        // this method run first one when the app install in devices
        // and put default value for all values
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDataAsyncTask(instance).execute();

        }
        // this method run all time when user open the app
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    private static class populateDataAsyncTask extends AsyncTask<Void , Void ,Void>{
        // to access the operation in side the class
        private wordsDao mWordsDao;
        populateDataAsyncTask(wordRoomDb database) {
            mWordsDao = database.wordsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new words("Book " , "Book" , "noun"));
            mWordsDao.insert(new words("Book " , "Book" , "noun"));
            mWordsDao.insert(new words("Book " , "Book" , "noun"));

            return null;
        }
    }
}
