package com.example.libraryapplicationproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedBook extends Fragment {


    public DetailedBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_detailed_book, container, false);
        String name = "";
        String author = "";
        String publisher = "";
        String date = "";
        String category = "";
        String img = "";
        String desc = "";
        int rating = 1;
         if (getArguments() != null){
           BookData data = getArguments().getParcelable("information");
            name = data.getBookName();
            author = data.getBookAuthor();
            publisher = data.getBookPublisher();
             date = data.getPublishedDate();
            category = data.getBookCat();
            img = data.getImageBook();
            desc = data.getBookDescription();
            rating = data.getBookRating();
       }
         TextView bookName = view.findViewById(R.id.detailBookName);
         TextView bookAuthor = view.findViewById(R.id.detailBookAuthor);
         TextView bookPub = view.findViewById(R.id.detailBookPublisher);
         TextView pubDate = view.findViewById(R.id.detailBookPubDate);
         TextView bookCat = view.findViewById(R.id.detailBookCat);
         ImageView bookImage = view.findViewById(R.id.detailBookImage);
         RatingBar bookRating = view.findViewById(R.id.ratingBar);
         TextView bookDesc = view.findViewById(R.id.detailBookDesc);

         bookName.setText(name);
         bookAuthor.setText(author);
         bookPub.setText(publisher);
         pubDate.setText(date);
         bookCat.setText(category);
        if (img.isEmpty()) { bookImage.setImageResource(R.drawable.placeholder);}
        else{ Picasso.get().load(img).into(bookImage);}
        bookRating.setRating(rating);
        bookDesc.setText(desc);
        return view;
    }

}
