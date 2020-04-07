package com.example.libraryapplicationproject.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.DatabaseHelper;
import com.example.libraryapplicationproject.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedBook extends Fragment {
    public String name = "";
    public  String author = "";
    public String publisher = "";
    public String date = "";
    public String category = "";
    public String img = "";
    public String desc = "";
    public String URL = "";
    public String previewURL = "";
    public int rating = 1;


    public DetailedBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detailed_book, container, false);
        Button favButton = view.findViewById(R.id.favouritesButton);
        if (CustomLockerAdapter.clickedFromLocker){
            favButton.setVisibility(View.INVISIBLE);
            CustomLockerAdapter.clickedFromLocker = false;
        }
        ImageView storeButton = view.findViewById(R.id.bookUrl);
        ImageView previewButton = view.findViewById(R.id.preview);
        name = "N/A";
        author = "N/A";
        publisher = "N/A";
        date = "N/A";
        category = "N/A";
        img = "N/A";
        desc = "N/A";
        URL = "";
        previewURL = "";
        rating = 1;
         if (getArguments() != null){
           BookData data = getArguments().getParcelable("information");
            name = data.getBookName();
            author = data.getBookAuthor();
            publisher = data.getBookPublisher();
             date = data.getPublishedDate();
            category = data.getBookCat();
            img = data.getBookImage();
            desc = data.getBookDescription();
            URL = data.getBookURL();
            previewURL = data.getPreview();
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
         bookDesc.setMovementMethod(new ScrollingMovementMethod());
         bookName.setText(name);
         bookAuthor.setText(author);
         bookPub.setText(publisher);
         pubDate.setText(date);
         bookCat.setText(category);
        if (img.isEmpty()) { bookImage.setImageResource(R.drawable.placeholder);}
        else{
            if (SettingsActivity.pictureSetting) {
                bookImage.setImageResource(R.drawable.placeholder);
            }else{
                Picasso.get().load(img).into(bookImage);
            }

            }
        bookRating.setRating(rating);
        bookDesc.setText(desc);


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                db.addBook(new BookData(name, author, category, publisher, date, img, desc, rating, URL));

                Navigation.findNavController(view).navigate(R.id.locker);
            }
        });
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(URL.isEmpty()){
                    Toast.makeText(getActivity(), "This book does not have a valid Buy Link",
                            Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewURL.isEmpty()){
                    Toast.makeText(getActivity(), "This book does not provide a preview",
                            Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(previewURL));
                    if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });
        return view;
    }

}
