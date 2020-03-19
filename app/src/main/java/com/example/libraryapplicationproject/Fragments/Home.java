package com.example.libraryapplicationproject.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.ArrayList;


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
    private BookAdapter adapt;
    private RecyclerView recycle;

    private BookAdapter adapt2;
    private RecyclerView recycle2;

    private BookAdapter adapt3;
    private RecyclerView recycle3;

    private BookAdapter adapt4;
    private RecyclerView recycle4;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_home, container, false);
         //array list to hold hour results
        final ArrayList<BookData> books = new ArrayList<>();
        final ArrayList<BookData> books2 = new ArrayList<>();
        final ArrayList<BookData> books3 = new ArrayList<>();
        final ArrayList<BookData> books4 = new ArrayList<>();

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


        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getContext());
        }

         //URLS FOR EACH RECYCLER VIEW
        //EACH WILL RETURN A DIFFERENT SUBJECT
        Uri url1 = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:Computer%20programs");
        Uri url2 = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:fiction");
        Uri url3 = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:Business%20&%20Economics%20/%20Entrepreneurship");
        Uri url4 = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:Drama");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json
                    String bookAuthor = "";
                    String bookCat = "";
                    String bookName = "";
                    String bookPub = "";
                    String pubDate = "";
                    String bookImage = "";
                    String bookDescription = "";
                    String cleanImageUrl = "";
                    int avgRating = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                            //json provided for author is array
                            JSONArray authors = resultInfo.getJSONArray("authors");

                            //if author is only one give me only one
                            if(authors.length() == 1){
                                bookAuthor = authors.getString(0);
                            }
                            //else give me all authors with "," in between
                            else{
                                bookAuthor = authors.getString(0) + ", " +authors.getString(1);
                            }
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                            bookPub = resultInfo.getString("publisher");
                            pubDate = resultInfo.getString("publishedDate");
                            bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                            bookDescription = resultInfo.getString("description");
                            avgRating = resultInfo.getInt("averageRating");
                            cleanImageUrl = bookImage.replace("http", "https");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(bookImage);
                        System.out.println(bookDescription);
                        System.out.println(avgRating);
                        books.add(new BookData(bookName, bookAuthor, bookCat,bookPub,pubDate,cleanImageUrl,bookDescription,avgRating));
                        adapt = new BookAdapter(books, getContext());
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






        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url2.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json
                    String bookAuthor = "";
                    String bookCat = "";
                    String bookName = "";
                    String bookPub = "";
                    String pubDate = "";
                    String bookImage = "";
                    String bookDescription = "";
                    String cleanImageUrl = "";
                    int avgRating = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                            //json provided for author is array
                            JSONArray authors = resultInfo.getJSONArray("authors");

                            //if author is only one give me only one
                            if(authors.length() == 1){
                                bookAuthor = authors.getString(0);
                            }
                            //else give me all authors with "," in between
                            else{
                                bookAuthor = authors.getString(0) + ", " +authors.getString(1);
                            }
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                            bookPub = resultInfo.getString("publisher");
                            pubDate = resultInfo.getString("publishedDate");
                            bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                            bookDescription = resultInfo.getString("description");
                            avgRating = resultInfo.getInt("averageRating");
                            cleanImageUrl = bookImage.replace("http", "https");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(bookImage);
                        System.out.println(bookDescription);
                        System.out.println(avgRating);
                        books2.add(new BookData(bookName, bookAuthor, bookCat,bookPub,pubDate,cleanImageUrl,bookDescription,avgRating));
                        adapt2 = new BookAdapter(books2, getContext());
                        recycle2.setAdapter(adapt2);
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
        requestQueue.add(jsonObjectRequest2);




        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, url3.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json
                    String bookAuthor = "";
                    String bookCat = "";
                    String bookName = "";
                    String bookPub = "";
                    String pubDate = "";
                    String bookImage = "";
                    String bookDescription = "";
                    String cleanImageUrl = "";
                    int avgRating = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                            //json provided for author is array
                            JSONArray authors = resultInfo.getJSONArray("authors");

                            //if author is only one give me only one
                            if(authors.length() == 1){
                                bookAuthor = authors.getString(0);
                            }
                            //else give me all authors with "," in between
                            else{
                                bookAuthor = authors.getString(0) + ", " +authors.getString(1);
                            }
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                            bookPub = resultInfo.getString("publisher");
                            pubDate = resultInfo.getString("publishedDate");
                            bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                            bookDescription = resultInfo.getString("description");
                            avgRating = resultInfo.getInt("averageRating");
                            cleanImageUrl = bookImage.replace("http", "https");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(bookImage);
                        System.out.println(bookDescription);
                        System.out.println(avgRating);
                        books3.add(new BookData(bookName, bookAuthor, bookCat,bookPub,pubDate,cleanImageUrl,bookDescription,avgRating));
                        adapt3 = new BookAdapter(books3, getContext());
                        recycle3.setAdapter(adapt3);
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
        requestQueue.add(jsonObjectRequest3);





        JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.GET, url4.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json
                    String bookAuthor = "";
                    String bookCat = "";
                    String bookName = "";
                    String bookPub = "";
                    String pubDate = "";
                    String bookImage = "";
                    String bookDescription = "";
                    String cleanImageUrl = "";
                    int avgRating = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                            //json provided for author is array
                            JSONArray authors = resultInfo.getJSONArray("authors");

                            //if author is only one give me only one
                            if(authors.length() == 1){
                                bookAuthor = authors.getString(0);
                            }
                            //else give me all authors with "," in between
                            else{
                                bookAuthor = authors.getString(0) + ", " +authors.getString(1);
                            }
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                            bookPub = resultInfo.getString("publisher");
                            pubDate = resultInfo.getString("publishedDate");
                            bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                            bookDescription = resultInfo.getString("description");
                            avgRating = resultInfo.getInt("averageRating");
                            cleanImageUrl = bookImage.replace("http", "https");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(bookImage);
                        System.out.println(bookDescription);
                        System.out.println(avgRating);
                        books4.add(new BookData(bookName, bookAuthor, bookCat,bookPub,pubDate,cleanImageUrl,bookDescription,avgRating));
                        adapt4 = new BookAdapter(books4, getContext());
                        recycle4.setAdapter(adapt4);
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
        requestQueue.add(jsonObjectRequest4);
        return view;
    }

}
