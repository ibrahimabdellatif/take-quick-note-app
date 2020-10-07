//this is the last thing in Room DataBase
package com.example.wordlibirarey;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
/*
this class 'View Model' is works with user so should be have all operation
 */
public class wordViewModel extends AndroidViewModel {

    private wordsRepository mRepository;
    private LiveData<List<words>> mAllWords;
    public wordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new wordsRepository(application);
        mAllWords = mRepository.getAllWords();
    }
    //call all operation
    public void insert(words word){
        mRepository.insert(word);
    }
    public void delete(words word){
        mRepository.delete(word);
    }
    public void update(words word){
        mRepository.update(word);
    }
    public void deleteAllWords(){
        mRepository.deleteAllWords();
    }
    public LiveData<List<words>> getAllWords(){
        return mRepository.getAllWords();
    }

}
