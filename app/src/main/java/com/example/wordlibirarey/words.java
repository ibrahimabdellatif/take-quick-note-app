//this class for entity object 'first thing in room data base'
package com.example.wordlibirarey;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*
this is annotation (@Entity) by that we can edit and more in data base like table name
or col name and so on , by default name of table is the same name of class but if we need to
change use this code (@Entity(tableName = "name of table"))
to set primary key use (@PrimaryKey(autoGenerate = true))
name of col is be the same of value name so if you want to change use ( @ColumnInfo(name = "name of col"))
 */

@Entity(tableName = "word Table")
public class words {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String wordName;
    private String wordMeaning;
    @ColumnInfo(name = "Type")
    private String wordType;
    //this is constructor we don't add id because we add the value by them self but
    // another string data user added by self so we create setter method for id
    public words(String wordName , String wordMeaning , String wordType){
        this.wordMeaning = wordMeaning;
        this.wordName = wordName;
        this.wordType=wordType;

    }
    public void setId(int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public String getWordName() {
        return wordName;
    }

    public String getWordType() {
        return wordType;
    }
}
