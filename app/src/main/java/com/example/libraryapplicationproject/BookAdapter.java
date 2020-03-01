package com.example.libraryapplicationproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private ArrayList<BookData> books;
    private Context context;

    /**
     * @author yonis
     * constructor
     * @since 2020-03-1
     * @param books ArrayList of books
     * @param context allows access to resources from classes and starts activities
     */
    public BookAdapter(ArrayList<BookData> books, Context context) {
        this.books = books;
        this.context = context;
    }

    /**
     * @author Yonis Sheek
     * @since 2020-03-1
     * @param parent
     * @param viewType
     * @return ViewHolder(view)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }


    /**
     * @since 2020-03-1
     * @param holder ViewHolder class
     * @param position position of list
     */
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {

    }

    /**
     * @since 2020-03-1
     * the size of the arrayList
     * @return ArrayList.size
     */
    @Override
    public int getItemCount() {
        //dont be stupid  and forget to change this Written by Yonis For Yonis
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
