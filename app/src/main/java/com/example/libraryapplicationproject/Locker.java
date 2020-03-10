package com.example.libraryapplicationproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Locker extends Fragment {


    public Locker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_locker, container, false);
        DatabaseHelper db = new DatabaseHelper(getContext());
        //in future this will pull items from database
        ArrayList<BookData> books = db.getAllBooks();
        CustomLockerAdapter adapter = new CustomLockerAdapter(books, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.lockerRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
