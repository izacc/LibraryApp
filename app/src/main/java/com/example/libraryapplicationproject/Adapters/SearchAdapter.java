package com.example.libraryapplicationproject.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yonis
 * @since 2020-03-15
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CustomSearchHolder>{

    private ArrayList<BookData> books;
    private Context context;

    public SearchAdapter(ArrayList<BookData> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.CustomSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CustomSearchHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
       return books.size();
    }

    public class CustomSearchHolder extends RecyclerView.ViewHolder {
        public CustomSearchHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
