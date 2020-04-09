package com.example.libraryapplicationproject.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.example.libraryapplicationproject.SettingsActivity;

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
    private ShimmerFrameLayout shimmer;
    private ShimmerFrameLayout shimmer2;
    private ShimmerFrameLayout shimmer3;
    private ShimmerFrameLayout shimmer4;
    private RecyclerView recycle;
    private RecyclerView recycle2;
    private RecyclerView recycle3;
    private RecyclerView recycle4;

    public static  ArrayList<String> categories = new ArrayList<>(Arrays.asList("Psychology", "History", "Philosophy", "Architecture", "Computers",
             "Mystery", "Science Fiction", "Thriller", "Romance",  "Mathematics", "Fiction", "Drama", "Juvenile"));
    public static ArrayList<String> queuedCategories = new ArrayList<>();
    public static Random randomCategory = new Random();
    public static boolean runOnlyOnce = true;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_home, container, false);

        shimmer = view.findViewById(R.id.shimmerFrameLayoutHome);
        shimmer2 = view.findViewById(R.id.shimmerFrameLayoutHome2);
        shimmer3 = view.findViewById(R.id.shimmerFrameLayoutHome3);
        shimmer4 = view.findViewById(R.id.shimmerFrameLayoutHome4);
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
        if(SettingsActivity.refreshSetting){
            queuedCategories.clear();
            for(int i = 0; i<= 4; i++) {
                String categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
                while (queuedCategories.contains(categorySearch)) {
                    categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
                }
                if (!queuedCategories.contains(categorySearch)) {
                    queuedCategories.add(categorySearch);

                }
            }
        }else{
            RefreshCategories();
        }


         //URLS FOR EACH RECYCLER VIEW
        //EACH WILL RETURN A DIFFERENT SUBJECT
        Uri url = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:" + categories.get(randomCategory.nextInt(categories.size())));


            AdapterPopulate(recycle,cat1,1);
            AdapterPopulate(recycle2,cat2,2);
            AdapterPopulate(recycle3,cat3,3);
            AdapterPopulate(recycle4,cat4,4);



            
        return view;
    }


    public static void RefreshCategories(){
        for(int i = 0; runOnlyOnce; i++) {
            String categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
            while (queuedCategories.contains(categorySearch)) {
                categorySearch = categories.get(randomCategory.nextInt(categories.size())).replace(" ", "%20");
            }
            if (!queuedCategories.contains(categorySearch)) {
                queuedCategories.add(categorySearch);

            }
            if (i >= 4){
                runOnlyOnce = false;
            }
        }

    }


    public void AdapterPopulate(final RecyclerView recycle, TextView category, int categoryNumber) {
        category.setText(queuedCategories.get(categoryNumber).replace("%20", " "));

       Uri url = Uri.parse("https://www.googleapis.com/books/v1/volumes?q=subject:" + queuedCategories.get(categoryNumber) + "&maxResults=40");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                shimmer.stopShimmer();
                shimmer.setVisibility(View.GONE);
                shimmer2.stopShimmer();
                shimmer2.setVisibility(View.GONE);
                shimmer3.stopShimmer();
                shimmer3.setVisibility(View.GONE);
                shimmer4.stopShimmer();
                shimmer4.setVisibility(View.GONE);
                String bookAuthor;
                String bookCat;
                String bookName;
                String bookPub;
                String pubDate;
                String bookImage;
                String bookDescription;
                String cleanImageUrl;
                String purchaseURL;
                String previewUrl;
                int avgRating = 0;
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    //data being retrieved from json

                    ArrayList<BookData> books = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                        try {
                            bookName = resultInfo.getString("title");
                        } catch (JSONException e) {
                            bookName = "N/A";
                            e.printStackTrace();
                        }

                        try{
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
                        } catch (JSONException e) {
                            bookAuthor = "N/A";
                            e.printStackTrace();
                        }
                        try{
                            bookCat = resultInfo.getJSONArray("categories").getString(0);
                        } catch (JSONException e) {
                            bookCat = "N/A";
                            e.printStackTrace();
                        }
                        try{
                            bookPub = resultInfo.getString("publisher");
                        } catch (JSONException e) {
                            bookPub = "N/A";
                            e.printStackTrace();
                        }
                        try{
                            pubDate = resultInfo.getString("publishedDate");
                        } catch (JSONException e) {
                            pubDate = "N/A";
                            e.printStackTrace();
                        }
                        try{
                            bookDescription = resultInfo.getString("description");
                        } catch (JSONException e) {
                            bookDescription = "N/A";
                            e.printStackTrace();
                        }
                        try{
                            previewUrl = resultInfo.getString("previewLink");
                        } catch (JSONException e) {
                            previewUrl = "";
                            e.printStackTrace();
                        }
                        try{
                            avgRating = resultInfo.getInt("averageRating");
                        } catch (JSONException e) {
                            avgRating = 0;
                            e.printStackTrace();
                        }
                        try{
                            purchaseURL = jsonObject.getJSONObject("saleInfo").getString("buyLink");
                        } catch (JSONException e) {
                            purchaseURL = "";
                            e.printStackTrace();


                        }
                        try{
                            bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                            cleanImageUrl = bookImage.replace("http", "https");
                        }catch (JSONException e){
                            cleanImageUrl = "";
                        }


                        books.add(new BookData(bookName, bookAuthor, bookCat, bookPub, pubDate, cleanImageUrl, bookDescription, avgRating, purchaseURL,previewUrl));
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
