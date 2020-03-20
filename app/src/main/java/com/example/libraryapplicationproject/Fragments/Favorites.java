package com.example.libraryapplicationproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.libraryapplicationproject.DatabaseHelper;
import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.R;


/**
 * A simple {@link Fragment} subclass.
 * @author yonis sheekh, Izaac Lucas
 * @since 2020-03-1
 */
public class Favorites extends Fragment {


    public Favorites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        Button seedButton = view.findViewById(R.id.seedButton);
        seedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DatabaseHelper db = new DatabaseHelper(getContext());
                db.addBook(new BookData("placeholder1","Author1","publisher1", 0, "www"));
                db.addBook(new BookData("placeholder2","Author2","publisher1" ,0, "www"));
                db.addBook(new BookData("placeholder3","Author3","publisher1" ,0, "www"));
                db.addBook(new BookData("placeholder4","Author4","publisher1" ,0, "www"));
                db.addBook(new BookData("placeholder5","Author5","publisher1" ,0, "www"));
                db.addBook(new BookData("placeholder6","Author6","publisher1" ,0, "www"));
                db.close();*/
            }
        });
        return view;
    }

}
