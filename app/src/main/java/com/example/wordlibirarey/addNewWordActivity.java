package com.example.wordlibirarey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class addNewWordActivity extends AppCompatActivity {
    private EditText wordEditText;
    private EditText wordType;
    private EditText wordMeaning;
    private boolean editMode;
    private int mID;
    public static final String EXTRA_ID = "com.example.wordlibirarey.extraid";
    //this variables as key for mainActivity
    public static final String EXTRA_Meaning = "com.example.wordlibirarey.extraMeaning";
    public static final String EXTRA_Type = "com.example.wordlibirarey.extraType";
    public static final String EXTRA_Word = "com.example.wordlibirarey.extraWord";

    //add new word view model
    private addNewWordViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
        wordEditText = findViewById(R.id.ed_word);
        wordType = findViewById(R.id.ed_word_type);
        wordMeaning = findViewById(R.id.ed_meaning);
        mViewModel = ViewModelProviders.of(this).get(addNewWordViewModel.class);
        /*
        add button to back the mainActivity add in mainfast file this line (android:parentActivityName=".MainActivity")
        and code in below
         */
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_exit);
        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID)){
            //Update words
            setTitle("Edit Word");
            editMode = true;
            //here EXTRA_ID word as key
            mID = i.getIntExtra(EXTRA_ID , -1);
            wordEditText.setText(i.getStringExtra(EXTRA_Word));
            wordMeaning.setText(i.getStringExtra(EXTRA_Meaning));
            wordType.setText(i.getStringExtra(EXTRA_Type));
        }else {
            //insert new word
            setTitle("Add New Word");
            editMode = false;
        }
        //change title of Activity
        setTitle("Add New Activity");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
    public void saveWord(){
        String word = wordEditText.getText().toString().trim();//trim() Clear blank spaces at the beginning and end of the line
        String type = wordType.getText().toString().trim();
        String meaning = wordMeaning.getText().toString().trim();

        words wordObject = new words(word , meaning ,type);
        //check if fields is empty or not
        if(word.isEmpty() || type.isEmpty() || meaning.isEmpty()){
            Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editMode) {
            wordObject.setId(mID);
            mViewModel.update(new words(word, meaning, type));
        }else{
            mViewModel.insert(new words(word ,meaning , type));
        }
        finish();
    }
}