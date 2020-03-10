package com.example.libraryapplicationproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
        DatabaseHelper db = new DatabaseHelper(context);
        final BookData book = books.get(position);
        holder.name.setText(book.getBookName());
        holder.author.setText(book.getBookAuthor());
        holder.description.setText(book.getBookDescription());
        //placeholder for image
        holder.bookImage.setImageResource(R.drawable.placeholder);
        holder.RatingSystemReader(book.getBookRating());
        db.close();
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
        protected ImageView star1;
        protected ImageView star2;
        protected ImageView star3;
        protected ImageView star4;
        protected ImageView star5;


        public void RatingSystemReader(int rating){
           switch(rating){
               case 1:
                   star1.setImageResource(R.drawable.ic_star_gold_24dp);
                   star2.setImageResource(R.drawable.ic_star_white_24dp);
                   star3.setImageResource(R.drawable.ic_star_white_24dp);
                   star4.setImageResource(R.drawable.ic_star_white_24dp);
                   star5.setImageResource(R.drawable.ic_star_white_24dp);
                   break;
               case 2:
                   star1.setImageResource(R.drawable.ic_star_gold_24dp);
                   star2.setImageResource(R.drawable.ic_star_gold_24dp);
                   star3.setImageResource(R.drawable.ic_star_white_24dp);
                   star4.setImageResource(R.drawable.ic_star_white_24dp);
                   star5.setImageResource(R.drawable.ic_star_white_24dp);
                   break;
               case 3:
                   star1.setImageResource(R.drawable.ic_star_gold_24dp);
                   star2.setImageResource(R.drawable.ic_star_gold_24dp);
                   star3.setImageResource(R.drawable.ic_star_gold_24dp);
                   star4.setImageResource(R.drawable.ic_star_white_24dp);
                   star5.setImageResource(R.drawable.ic_star_white_24dp);
                   break;
               case 4:
                   star1.setImageResource(R.drawable.ic_star_gold_24dp);
                   star2.setImageResource(R.drawable.ic_star_gold_24dp);
                   star3.setImageResource(R.drawable.ic_star_gold_24dp);
                   star4.setImageResource(R.drawable.ic_star_gold_24dp);
                   star5.setImageResource(R.drawable.ic_star_white_24dp);
                   break;
               case 5:
                   star1.setImageResource(R.drawable.ic_star_gold_24dp);
                   star2.setImageResource(R.drawable.ic_star_gold_24dp);
                   star3.setImageResource(R.drawable.ic_star_gold_24dp);
                   star4.setImageResource(R.drawable.ic_star_gold_24dp);
                   star5.setImageResource(R.drawable.ic_star_gold_24dp);
                   break;
                   default:
                       break;


           }
        }
        public void RatingSystemClicks(){
            final DatabaseHelper db = new DatabaseHelper(context);

            star1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star1.setImageResource(R.drawable.ic_star_gold_24dp);
                    star2.setImageResource(R.drawable.ic_star_white_24dp);
                    star3.setImageResource(R.drawable.ic_star_white_24dp);
                    star4.setImageResource(R.drawable.ic_star_white_24dp);
                    star5.setImageResource(R.drawable.ic_star_white_24dp);
                    books.get(getLayoutPosition()).setBookRating(1);
                    db.updateRating(books.get(getLayoutPosition()));
                    db.close();
                }
            });
            star2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star1.setImageResource(R.drawable.ic_star_gold_24dp);
                    star2.setImageResource(R.drawable.ic_star_gold_24dp);
                    star3.setImageResource(R.drawable.ic_star_white_24dp);
                    star4.setImageResource(R.drawable.ic_star_white_24dp);
                    star5.setImageResource(R.drawable.ic_star_white_24dp);
                    books.get(getLayoutPosition()).setBookRating(2);
                    db.updateRating(books.get(getLayoutPosition()));
                    db.close();
                }
            });
            star3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star1.setImageResource(R.drawable.ic_star_gold_24dp);
                    star2.setImageResource(R.drawable.ic_star_gold_24dp);
                    star3.setImageResource(R.drawable.ic_star_gold_24dp);
                    star4.setImageResource(R.drawable.ic_star_white_24dp);
                    star5.setImageResource(R.drawable.ic_star_white_24dp);
                    books.get(getLayoutPosition()).setBookRating(3);
                    db.updateRating(books.get(getLayoutPosition()));
                    db.close();

                }
            });
            star4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star1.setImageResource(R.drawable.ic_star_gold_24dp);
                    star2.setImageResource(R.drawable.ic_star_gold_24dp);
                    star3.setImageResource(R.drawable.ic_star_gold_24dp);
                    star4.setImageResource(R.drawable.ic_star_gold_24dp);
                    star5.setImageResource(R.drawable.ic_star_white_24dp);
                    books.get(getLayoutPosition()).setBookRating(4);
                    db.updateRating(books.get(getLayoutPosition()));
                    db.close();
                }
            });
            star5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star1.setImageResource(R.drawable.ic_star_gold_24dp);
                    star2.setImageResource(R.drawable.ic_star_gold_24dp);
                    star3.setImageResource(R.drawable.ic_star_gold_24dp);
                    star4.setImageResource(R.drawable.ic_star_gold_24dp);
                    star5.setImageResource(R.drawable.ic_star_gold_24dp);
                    books.get(getLayoutPosition()).setBookRating(5);
                    db.updateRating(books.get(getLayoutPosition()));
                    db.close();

                }
            });
        }

        public CustomViewHolder(final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.bookName);
            this.author = itemView.findViewById(R.id.bookAuthor);
            this.description = itemView.findViewById(R.id.bookDescription);
            this.bookImage = itemView.findViewById(R.id.bookImage);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
            this.star1 = itemView.findViewById(R.id.star1);
            this.star2 = itemView.findViewById(R.id.star2);
            this.star3 = itemView.findViewById(R.id.star3);
            this.star4 = itemView.findViewById(R.id.star4);
            this.star5 = itemView.findViewById(R.id.star5);

            RatingSystemClicks();

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete " + books.get(
                                    getLayoutPosition()).getBookName()  + " from your locker?")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton("No", null)
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHelper db = new DatabaseHelper(context);
                                    db.deleteBook(books.get(
                                            getLayoutPosition()).getBookID());
                                    books.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    db.close();
                                }
                            })
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