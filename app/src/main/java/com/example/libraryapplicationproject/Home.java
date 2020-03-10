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
 * @author yonis sheekh
 * @since 2020-03-2
 *
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    public static ArrayList<BookData> books = new ArrayList<>();

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_home, container, false);


         /*
         this is just placeholder to showcase  how home screen will (hopefully look like) after API implementation
          */

        books.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder, 0));
        books.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder, 0));
        books.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder,0 ));
        books.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder,0));
        books.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder,0));
        books.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder,0));

        ArrayList<BookData> data = new ArrayList<>();
        data.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder));
        data.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder));
        data.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder));
        data.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder));
        data.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder));
        data.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder));


        ArrayList<BookData> data2 = new ArrayList<>();
        data2.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder));
        data2.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder));
        data2.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder));
        data2.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder));
        data2.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder));
        data2.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder));


        ArrayList<BookData> data3 = new ArrayList<>();
        data3.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder));
        data3.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder));
        data3.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder));
        data3.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder));
        data3.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder));
        data3.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder));


        ArrayList<BookData> data4 = new ArrayList<>();
        data4.add(new BookData("placeholder1","Author1","publisher1",R.drawable.placeholder));
        data4.add(new BookData("placeholder2","Author2","publisher2",R.drawable.placeholder));
        data4.add(new BookData("placeholder3","Author3","publisher3",R.drawable.placeholder));
        data4.add(new BookData("placeholder4","Author4","publisher4",R.drawable.placeholder));
        data4.add(new BookData("placeholder5","Author5","publisher5",R.drawable.placeholder));
        data4.add(new BookData("placeholder6","Author6","publisher6",R.drawable.placeholder));



        RecyclerView recycle = view.findViewById(R.id.recyclerFirstRow);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recycle.setLayoutManager(manager);
        BookAdapter adapt = new BookAdapter(data,getContext());
        recycle.setAdapter(adapt);

        RecyclerView recycle2 = view.findViewById(R.id.recyclerSecondRow);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(RecyclerView.HORIZONTAL);
        recycle2.setLayoutManager(manager2);
        BookAdapter adapt2 = new BookAdapter(data2,getContext());
        recycle2.setAdapter(adapt2);

        RecyclerView recycle3 = view.findViewById(R.id.recyclerThirdRow);
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext());
        manager3.setOrientation(RecyclerView.HORIZONTAL);
        BookAdapter adapt3 = new BookAdapter(data3,getContext());
        recycle3.setLayoutManager(manager3);
        recycle3.setAdapter(adapt3);

        RecyclerView recycle4 = view.findViewById(R.id.recyclerFourthRow);
        LinearLayoutManager manager4 = new LinearLayoutManager(getContext());
        manager4.setOrientation(RecyclerView.HORIZONTAL);
        BookAdapter adapt4 = new BookAdapter(data4,getContext());
        recycle4.setLayoutManager(manager4);
        recycle4.setAdapter(adapt4);

        return view;
    }

}
