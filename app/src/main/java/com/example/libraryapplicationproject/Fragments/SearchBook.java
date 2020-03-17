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
import android.widget.Toast;

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
 * @since 2020-03-9
 * A simple {@link Fragment} subclass.
 */
public class SearchBook extends Fragment {

    //searchBar
    private EditText search;
    //submitButton
    private Button searchButton;
    //request.
    private RequestQueue requestQueue;
    //recycler adapter
    private SearchAdapter adapt;
    private RecyclerView recycle;
    public SearchBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_search_book, container, false);
         search = view.findViewById(R.id.searchBar);
         searchButton = view.findViewById(R.id.searchButton);
         final ArrayList<BookData> books = new ArrayList<>();
         recycle = view.findViewById(R.id.searchResults);
         LinearLayoutManager manager = new LinearLayoutManager(getContext());
         recycle.setLayoutManager(manager);


        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getContext());
        }
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear the list every time a search is made
                books.clear();
                String userSearch = search.getText().toString();
                //replace every space the user enters with plus so url can be read properly
               String quickfix = userSearch.replace(" ", "+");
                if(userSearch.equals("")){
                    //no books
                    Toast.makeText(getContext(),"Enter a book",Toast.LENGTH_SHORT).show();
                }else{
                    //base url with the users entry
                    Uri uriSearch = Uri.parse("https://www.googleapis.com/books/v1/volumes?q="+quickfix);
//                    Uri.Builder builder = uriSearch.buildUpon();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uriSearch.toString(), null,
                            new Response.Listener<JSONObject>() {
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
                                            adapt = new SearchAdapter(books, getContext());
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
        });
         return view;
    }

}
