package com.example.libraryapplicationproject.Adapters;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yonis sheekh :)
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search,parent,false);
        return new SearchAdapter.CustomSearchHolder(view);
    }
    /**
     * @since 2020-03-15
     * @param holder ViewHolder class
     * @param position position of list
     */
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CustomSearchHolder holder, int position) {
        BookData item = books.get(position);
        holder.bookName.setText(item.getBookName());
        holder.bookAuthor.setText(item.getBookAuthor());
        holder.bookPub.setText(item.getBookPublisher());
        holder.pubDate.setText(item.getPublishedDate());
        holder.bookCat.setText(item.getBookCat());
        Picasso.get().load(item.getImageBook()).into(holder.bookImage);

    }
    /**
     * @since 2020-03-15
     * the size of the arrayList
     * @return ArrayList.size
     */
    @Override
    public int getItemCount() {
       return books.size();
    }

    public class CustomSearchHolder extends RecyclerView.ViewHolder {
        protected TextView bookName;
        protected TextView bookAuthor;
        protected TextView bookPub;
        protected TextView pubDate;
        protected TextView bookCat;
        protected ImageView bookImage;

        public CustomSearchHolder(@NonNull View itemView) {
            super(itemView);
            this.bookName = itemView.findViewById(R.id.bookNameSearch);
            this.bookAuthor = itemView.findViewById(R.id.bookAuthorNameSearch);
            this.bookCat = itemView.findViewById(R.id.bookCatSearch);
            this.bookPub = itemView.findViewById(R.id.bookPublisherSearch);
            this.pubDate = itemView.findViewById(R.id.bookPublisherDateSearch);
            this.bookImage = itemView.findViewById(R.id.bookImageSearch);
        }
    }
}
