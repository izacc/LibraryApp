package com.example.libraryapplicationproject.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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

        BookData item = books.get(position);
        holder.name.setText(item.getBookName());
        if (item.getImageBook().isEmpty()) {
            holder.image.setImageResource(R.drawable.placeholder);
        } else{
            Picasso.get().load(item.getImageBook()).into(holder.image);
        }


    }

    /**
     * @since 2020-03-1
     * the size of the arrayList
     * @return ArrayList.size
     */
    @Override
    public int getItemCount() {
        //dont be stupid  and forget to change this Written by Yonis For Yonis
        //good job u didnt forget this time :D
        return books.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         protected TextView name;
         protected ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.image = itemView.findViewById(R.id.bookImage);

            itemView.setOnClickListener(this);
        }
         @Override
         public void onClick(View view) {
             Bundle bundle = new Bundle();
             bundle.putParcelable("information", books.get(getAdapterPosition()));
             Navigation.findNavController(view).navigate(R.id.action_home_to_detailedBook,bundle);
         }
     }
}
