package com.example.aqeb_.shopifyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private List<Integer> data = new ArrayList<>();
    private ArrayList<String> inventorySumList = new ArrayList<>();
    private ArrayList<String> imageURLS = new ArrayList<>();
    private List<String> productTitles = new ArrayList<String>();
    String selectedTag;
    Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_product_list);

         extras = getIntent().getExtras();
         if(extras!=null) {
             selectedTag = extras.getString("Title");
             for (Products listItem : MainActivity.data) {
                 if (listItem.getTitle().contains(selectedTag)) {
                     productTitles.add(listItem.getTitle());
                     imageURLS.add(listItem.getImages().get(0).getSrc());
                     for(Variants items: listItem.getVariants()){
                         String numberConversion = items.getInventory_quantity();
                         int value = Integer.parseInt(numberConversion);
                         data.add(value);
                     }
                     int sum = 0;
                     for (int i=0; i<data.size(); i++){
                         sum += data.get(i);
                     }
                     data.clear();
                     String stringValue = Integer.toString(sum);
                     inventorySumList.add(stringValue);
                 }
             }
         }
         initRecyclerViewProductList();

    }

    public void initRecyclerViewProductList(){//initializes ProductListAdapter and its recycler view and creates dividers

        try {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_products);
            ProductListAdapter adapter = new ProductListAdapter(this, productTitles, imageURLS, inventorySumList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    new LinearLayoutManager(this).getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        catch (WindowManager.BadTokenException e) {
            Log.d("ProductListAdapter","Error at starting the recycler");
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
