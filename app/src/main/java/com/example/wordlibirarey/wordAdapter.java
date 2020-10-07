package com.example.wordlibirarey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class wordAdapter extends RecyclerView.Adapter<wordAdapter.wordViewHolder> {
    //member variables
    private static List<words> mWordList = new ArrayList<>();
    private static OnItemClickListener mlistener;
    @NonNull
    @Override
    // call items in layout and attached them with viewHolder
    public wordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//parent.getContext refer to mainActivity
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.words_list_view , parent , false);
        return new wordViewHolder(itemView);
    }

    @Override
    // put the values to items in layout 'type of TextView'
    public void onBindViewHolder(@NonNull wordViewHolder holder, int position) {
     //now value of mWordList is null so we put value to it by get(position) 'position full value auto by adapter'
        words currentWord = mWordList.get(position);//this is an object from words class so we can use all method inside class
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());
    }

    /*
    make setWordList method to make value od mWordList Updated all time
     */
    public void setWordList(List<words> words){
        mWordList = words;
        //to update recyclerView after any changes NOTICE'notifyDataSetChanged() refused add any animation to the view'
        notifyDataSetChanged();
    }
    @Override
    //return number of items in layout
    public int getItemCount() {
        return mWordList.size();
    }

    public static class wordViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;
        public wordViewHolder(@NonNull View itemView) {
            super(itemView);
            //after define item '3 textView we attach them by ID in wordViewHolder constructor'
            textViewWord = itemView.findViewById(R.id.word_text_view);
            textViewMeaning = itemView.findViewById(R.id.meaning_text_view);
            textViewType = itemView.findViewById(R.id.type_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if(mlistener != null && index!= RecyclerView.NO_POSITION){
                        mlistener.onItemClick(mWordList.get(index));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(words word);
    }
    public void onItemClickListener(OnItemClickListener listener){
     mlistener = listener;
    }
    public words getpos(int pos){
        return mWordList.get(pos);
    }
}
