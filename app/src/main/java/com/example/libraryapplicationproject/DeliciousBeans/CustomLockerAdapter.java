package com.example.libraryapplicationproject.DeliciousBeans;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libraryapplicationproject.DatabaseHelper;
import com.example.libraryapplicationproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLockerAdapter extends RecyclerView.Adapter<CustomLockerAdapter.CustomViewHolder> {


    private ArrayList<BookData> books;
    private Context context;

    public CustomLockerAdapter(ArrayList<BookData> books, Context context) {
        this.books = books;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locker_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        final BookData book = books.get(position);
        holder.name.setText(book.getBookName());
        holder.author.setText(book.getBookAuthor());
        holder.description.setText(book.getBookDescription());
        //placeholder for image
        holder.bookImage.setImageResource(R.drawable.placeholder);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class CustomViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        protected TextView name;
        protected TextView author;
        protected TextView description;
        protected ImageView bookImage;
        protected ImageView deleteButton;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.bookName);
            this.author = itemView.findViewById(R.id.bookAuthor);
            this.description = itemView.findViewById(R.id.bookDescription);
            this.bookImage = itemView.findViewById(R.id.bookImage);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete " + books.get(
                                    getLayoutPosition()).getBookName() + "?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHelper db = new DatabaseHelper(context);
                                    db.deleteLockerItem(books.get(
                                            getLayoutPosition()).getBookID());
                                    books.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    db.close();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            //Can add show more area here
        }
    }
}
