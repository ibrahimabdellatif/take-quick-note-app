package com.example.wordlibirarey;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class wordsRepository {
   //object from wordsDao interface
    private wordsDao mwordsDoa;
    //liveData variable
    private LiveData<List<words>> getAllWords;
    //constructor form this class to access the data
    wordsRepository(Application app){

        wordRoomDb db= wordRoomDb.getInstance(app);
        mwordsDoa = db.wordsDao();
        getAllWords = mwordsDoa.getAllWords();


    }
    //operation TODO make this operation in new thread because main thread don't make this operations ..

    //insert
    public void insert(words word){
    new InsertAsyncTask(mwordsDoa).execute(word);
    }
    //update
    public void update(words word){
    new UpdateAsyncTask(mwordsDoa).execute(word);
    }
    //delete
    public void delete(words word){
    new DeleteAsyncTask(mwordsDoa).execute(word);
    }
    //getAllWords
    public LiveData<List<words>> getAllWords(){

        return getAllWords;
    }
    //deleteAllWords
    public void deleteAllWords(){
    new DeleteAllWordsAsyncTask(mwordsDoa).execute();
    }

    public static class InsertAsyncTask extends AsyncTask<words , Void , Void>{
        private wordsDao mwordsDao;
        public InsertAsyncTask (wordsDao wordsDao){
            mwordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(words... words) {
            mwordsDao.insert(words[0]);
            return null;

        }
    }

    private static class DeleteAsyncTask extends AsyncTask<words, Void, Void> {
        private wordsDao mwordsDoa;

        public DeleteAsyncTask(wordsDao wordsDao) {
            mwordsDoa = wordsDao;
        }

        @Override
        protected Void doInBackground(words... words) {
            mwordsDoa.delete(words[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<words, Void, Void> {
        private wordsDao mwordsDoa;

        public UpdateAsyncTask(wordsDao wordsDao) {
            mwordsDoa = wordsDao;
        }

        @Override
        protected Void doInBackground(words... words) {
            mwordsDoa.update(words[0]);
            return null;
        }
    }
    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private wordsDao mwordsDoa;

        public DeleteAllWordsAsyncTask(wordsDao wordsDao) {
            mwordsDoa = wordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mwordsDoa.deleteAllWords();
            return null;
        }
    }
}
