package com.example.libraryapplicationproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.DeliciousBeans.CustomLockerAdapter;
import com.example.libraryapplicationproject.R;

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
        ArrayList<BookData> books = new ArrayList<>();
        books.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder));
        books.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder));
        books.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder));
        books.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder));
        books.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder));
        books.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder));
        CustomLockerAdapter adapter = new CustomLockerAdapter(books, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.lockerRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
