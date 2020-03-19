package com.example.libraryapplicationproject.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.libraryapplicationproject.Adapters.BookAdapter;
import com.example.libraryapplicationproject.Adapters.SearchAdapter;
import com.example.libraryapplicationproject.DeliciousBeans.BookData;
import com.example.libraryapplicationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * @author yonis sheekh
 * @since 2020-03-18
 *
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    //request.
    private RequestQueue requestQueue;
    //recycler adapter

    private RecyclerView recycle;
    private RecyclerView recycle2;
    private RecyclerView recycle3;
    private RecyclerView recycle4;


    public static  ArrayList<String> categories = new ArrayList<>(Arrays.asList("Psychology", "History", "Political Science", "Philosophy", "Architecture", "Computers",
            "Literary Collections", "Mathematics", "English literature", "Fiction", "Drama", "Juvenile", "Computer Programs"));
    public static ArrayList<String> queuedCategories = new ArrayList<>();
    public static Random randomCategory = new Random();
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_home, container, false);
         //array list to hold hour results

        recycle = view.findViewById(R.id.recyclerFirstRow);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recycle.setLayoutManager(manager);

        recycle2 = view.findViewById(R.id.recyclerSecondRow);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(RecyclerView.HORIZONTAL);
        recycle2.setLayoutManager(manager2);

        recycle3 = view.findViewById(R.id.recyclerThirdRow);
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext());
        manager3.setOrientation(RecyclerView.HORIZONTAL);
        recycle3.setLayoutManager(manager3);

        recycle4 = view.findViewById(R.id.recyclerFourthRow);
        LinearLayoutManager manager4 = new LinearLayoutManager(getContext());
        manager4.setOrientation(RecyclerView.HORIZONTAL);
        recycle4.setLayoutManager(manager4);

        TextView cat1 = view.findViewById(R.id.category1);
        TextView cat2 = view.findViewById(R.id.category2);
        TextView cat3 = view.findViewById(R.id.category3);
        TextView cat4 = view.findViewById(R.id.category4);

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getContext());
        }

        //If there is a way to pull all categories do that instead



         //URLS FOR EACH RECYCLER VIEW
        //EACH WILL RETURN A DIFFERENT SUBJECT
        Uri url = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:" + categories.get(randomCategory.nextInt(categories.size())));


            adapterPopulater(recycle,cat1);
            adapterPopulater(recycle2,cat2);
            adapterPopulater(recycle3,cat3);
            adapterPopulater(recycle4,cat4);
            queuedCategories.clear();


        return view;
    }

    public void adapterPopulater(final RecyclerView recycle, TextView category) {
       String categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
       while (queuedCategories.contains(categorySearch)){
           categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
       }
       if (!queuedCategories.contains(categorySearch))  {
           queuedCategories.add(categorySearch);
           category.setText(categorySearch.replace("%20", " "));
       }



       Uri url = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:" + categorySearch + "&maxResults=40");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json
                    String bookAuthor = "N/A";
                    String bookCat = "N/A";
                    String bookName = "N/A";
                    String bookPub = "N/A";
                    String pubDate = "N/A";
                    String bookImage = "N/A";
                    String bookDescription = "N/A";
                    String cleanImageUrl = "N/A";
                    int avgRating = 0;
                    ArrayList<BookData> books = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                            //json provided for author is array
                            JSONArray authors = resultInfo.getJSONArray("authors");

                            //if author is only one give me only one
                            if (authors.length() == 1) {
                                bookAuthor = authors.getString(0);
                            }
                            //else give me all authors with "," in between
                            else {
                                bookAuthor = authors.getString(0) + ", " + authors.getString(1);
                            }
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                            bookPub = resultInfo.getString("publisher");
                            pubDate = resultInfo.getString("publishedDate");
                            bookDescription = resultInfo.getString("description");
                            avgRating = resultInfo.getInt("averageRating");







                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                        //Moved out of try and it works now
                        bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                        cleanImageUrl = bookImage.replace("http", "https");

                        books.add(new BookData(bookName, bookAuthor, bookCat, bookPub, pubDate, cleanImageUrl, bookDescription, avgRating));
                        BookAdapter adapt = new BookAdapter(books, getContext());
                        recycle.setAdapter(adapt);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
