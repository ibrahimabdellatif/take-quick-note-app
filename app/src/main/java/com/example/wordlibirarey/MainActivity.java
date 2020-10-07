package com.example.wordlibirarey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //viewModel
    private wordViewModel mWordViewModel;
    //RecyclerView
    private RecyclerView mRecyclerView;
    private wordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //floating button
        FloatingActionButton floatingActionButton = findViewById(R.id.add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , addNewWordActivity.class);
                //to return the result
                startActivityForResult(i , 1);
            }
        });

        //recycle View
        mRecyclerView = findViewById(R.id.words_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //mean all item have the same size
        mRecyclerView.setHasFixedSize(true);

        //connect recycle view with adapter
        mWordAdapter = new wordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);

        //view Model
        //make new object from wordViewModel
        mWordViewModel = ViewModelProviders.of(this).get(wordViewModel.class);
        /*
         here we call 'getAllWords' method and put observer 'مراقب'
         To notice changes like update,delete and insert .
         observer it watch the app by life cycle if app is on it make refresh if it close
         make clean
         */
        mWordViewModel.getAllWords().observe(this, new Observer<List<words>>() {
            @Override
            public void onChanged(List<words> words) {
                //Update UI
                //RecyclerView
                mWordAdapter.setWordList(words);
                //Toast.makeText(MainActivity.this, "onChanged is work", Toast.LENGTH_SHORT).show();
            }
        });
        mWordAdapter.onItemClickListener(new wordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(words word) {
                Intent i = new Intent(MainActivity.this , addNewWordActivity.class );
                i.putExtra(addNewWordActivity.EXTRA_ID , word.getId());
                i.putExtra(addNewWordActivity.EXTRA_Word , word.getWordName());
                i.putExtra(addNewWordActivity.EXTRA_Meaning , word.getWordMeaning());
                i.putExtra(addNewWordActivity.EXTRA_Type , word.getWordType());

                startActivity(i);
            }
        });
        //swipe to delete item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            //we don't need to this method so put 0 as parameter
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              //delete item
                int position = viewHolder.getAdapterPosition();
                mWordViewModel.delete(mWordAdapter.getpos(position));
            }
        }).attachToRecyclerView(mRecyclerView);
    }
}