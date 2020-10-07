package com.example.wordlibirarey;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


public class addNewWordViewModel extends AndroidViewModel {
    private wordsRepository mRepository;
    public addNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new wordsRepository(application);
    }
    //call all operation
    public void insert(words word){
        mRepository.insert(word);
    }

    public void update(words word){
        mRepository.update(word);
    }


}
