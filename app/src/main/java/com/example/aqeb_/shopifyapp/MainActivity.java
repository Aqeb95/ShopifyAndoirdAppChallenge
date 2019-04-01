package com.example.aqeb_.shopifyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> sortedTags = new ArrayList<>();
    private HashSet<String> hashSet = new HashSet<String>();
    public static ArrayList<Products> data;
    TagsListAdapter adapter;
    String[] splitData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<JSONResponse> call = api.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                    JSONResponse jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getProducts()));

                for(Products p: data){
                    splitData =  (p.getTags().replaceAll("\\s","")).split(",");
                    sortedTags.addAll(Arrays.asList(splitData));
                }

                hashSet.addAll(sortedTags);
                sortedTags.clear();
                sortedTags.addAll(hashSet);

                initRecyclerView();
                searchFilter();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchFilter() { //edit text responses for the search bar
        EditText editText = findViewById(R.id.search_edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    private void filter(String text){ //filter function for the search bar
        ArrayList<String> filteredList = new ArrayList<>();

        for(String item: sortedTags){
            if(item.toLowerCase().contains(text.toLowerCase())){
                filteredList.add((item));
            }
        }
        adapter.filteredList(filteredList);
    }

    public void initRecyclerView(){ //initializes TagListAdapter and its recycler view and creates dividers

        try {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
             adapter = new TagsListAdapter(this,sortedTags,data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    new LinearLayoutManager(this).getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        catch (WindowManager.BadTokenException e) {
            Log.d("TagsListAdapter","Error at starting the recycler");
        }
    }

    private void fullScreen(){ //hides the action bar and sets window to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
