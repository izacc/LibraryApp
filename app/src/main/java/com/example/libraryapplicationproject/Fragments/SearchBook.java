package com.example.libraryapplicationproject.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
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
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * @author yonis sheekh
 * @since 2020-03-9
 * A simple {@link Fragment} subclass.
 */
public class SearchBook extends Fragment {
    private ShimmerFrameLayout shimmer;
    //searchBar
    private SearchView search;
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
         final View view = inflater.inflate(R.layout.fragment_search_book, container, false);
         search = view.findViewById(R.id.searchBar);
         final ArrayList<BookData> books = new ArrayList<>();
         recycle = view.findViewById(R.id.searchResults);
         shimmer = view.findViewById(R.id.shimmerFrameLayout);
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
         LinearLayoutManager manager = new LinearLayoutManager(getContext());
         recycle.setLayoutManager(manager);
        final TextView temp = view.findViewById(R.id.temptext);

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getContext());
        }
       search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               //clear the list every time a search is made
               temp.setVisibility(View.GONE);
               books.clear();
               shimmer.startShimmer();
               shimmer.setVisibility(View.VISIBLE);
               String userSearch = search.getQuery().toString();
               //replace every space the user enters with plus so url can be read properly
               String quickfix = userSearch.replace(" ", "+");
               if(userSearch.equals("")){
                   //no books
                   Toast.makeText(getContext(),"Enter a book",Toast.LENGTH_SHORT).show();
               }else{
                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           shimmer.stopShimmer();
                           shimmer.setVisibility(View.GONE);
                       }
                   },2000);
                   //base url with the users entry
                   Uri uriSearch = Uri.parse("https://www.googleapis.com/books/v1/volumes?q="+quickfix+"&filter=ebooks&maxResults=40");
//                    Uri.Builder builder = uriSearch.buildUpon();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uriSearch.toString(), null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        JSONArray jsonArray = response.getJSONArray("items");
                                        //data being retrieved from json
                                        String bookAuthor;
                                        String bookCat;
                                        String bookName;
                                        String bookPub;
                                        String pubDate;
                                        String bookImage;
                                        String bookDescription;
                                        String cleanImageUrl;
                                        String purchaseURL;
                                        String previewURL;
                                        int avgRating = 0;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            JSONObject resultInfo = jsonObject.getJSONObject("volumeInfo");

                                            try {
                                                bookName = resultInfo.getString("title");
                                            } catch (JSONException e) {
                                                bookName = "N/A";
                                                e.printStackTrace();
                                            }

                                            try {
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
                                            try {
                                                bookCat = resultInfo.getJSONArray("categories").getString(0);
                                            } catch (JSONException e) {
                                                bookCat = "N/A";
                                                e.printStackTrace();
                                            }
                                            try {
                                                bookPub = resultInfo.getString("publisher");
                                            } catch (JSONException e) {
                                                bookPub = "N/A";
                                                e.printStackTrace();
                                            }
                                            try {
                                                pubDate = resultInfo.getString("publishedDate");
                                            } catch (JSONException e) {
                                                pubDate = "N/A";
                                                e.printStackTrace();
                                            }
                                            try {
                                                bookDescription = resultInfo.getString("description");
                                            } catch (JSONException e) {
                                                bookDescription = "N/A";
                                                e.printStackTrace();
                                            }

                                            try {
                                                previewURL = resultInfo.getString("previewLink");
                                            } catch (JSONException e) {
                                                previewURL = "";
                                                e.printStackTrace();
                                            }
                                            try {
                                                avgRating = resultInfo.getInt("averageRating");
                                            } catch (JSONException e) {
                                                avgRating = 0;
                                                e.printStackTrace();
                                            }
                                            try {
                                                purchaseURL = jsonObject.getJSONObject("saleInfo").getString("buyLink");
                                            } catch (JSONException e) {
                                                purchaseURL = "";
                                                e.printStackTrace();
                                            }
                                            try {
                                                bookImage = resultInfo.getJSONObject("imageLinks").getString("thumbnail");
                                                cleanImageUrl = bookImage.replace("http", "https");
                                            } catch (JSONException e) {
                                                cleanImageUrl = "";
                                            }


                                            books.add(new BookData(bookName, bookAuthor, bookCat, bookPub, pubDate, cleanImageUrl, bookDescription, avgRating, purchaseURL,previewURL));
                                            SearchAdapter adapt = new SearchAdapter(books, getContext());
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
               return false;
           }
           @Override
           public boolean onQueryTextChange(String s) {
               return false;
           }
       });

         return view;
    }
}
