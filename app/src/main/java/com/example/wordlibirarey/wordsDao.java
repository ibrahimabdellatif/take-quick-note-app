//this interface for Dao is simple and just call method that we make it in entity class
// 'second thing in room database'
package com.example.wordlibirarey;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface wordsDao {
    @Insert
    void insert(words word);
    @Update
    void update(words word);
    @Delete
    void delete(words word);
    @Query("DELETE FROM `word table`")
    void deleteAllWords();
    @Query("SELECT * FROM `word table`")
    //we put List<words> inside LiveData object because we always watch the data and words
    // if there update or delete or make new insert to be refreshed all the time
    LiveData<List<words>> getAllWords();
}
