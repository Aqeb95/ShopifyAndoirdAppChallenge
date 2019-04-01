package com.example.aqeb_.shopifyapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    private Context context;
    private List<String> productName;
    private List<String> imageURLs;
    private List<String> inventoryQuantity;
    Typeface typeface;
    TextView titleText, inventoryText;
    View view;


    public ProductListAdapter(Context context, List<String> productName, List<String> imageURLs, List<String> inventoryQuantity) {
        this.context = context;
        this.productName = productName;
        this.inventoryQuantity = inventoryQuantity;
        this.imageURLs = imageURLs;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_listitem, parent, false);
        ProductListAdapter.ViewHolder holder = new ProductListAdapter.ViewHolder(view);
        setFont();
        return holder;
    }

    private void setFont(){ //sets new font for the text
        typeface = Typeface.createFromAsset(context.getAssets(), "caviardreams_bold.ttf");

        titleText = view.findViewById(R.id.text_product);
        inventoryText = view.findViewById(R.id.text_inventory);

        titleText.setTypeface(typeface);
        inventoryText.setTypeface(typeface);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, final int position) {
        holder.titleText.setText(productName.get(position));
        holder.inventoryText.setText("Inventory Quantity: " + inventoryQuantity.get(position));

        Glide.with(context)
                .asBitmap()
                .load(imageURLs.get(position))
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return productName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, inventoryText;
        RelativeLayout layoutItem;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            inventoryText = itemView.findViewById(R.id.text_inventory);
            titleText = itemView.findViewById(R.id.text_product);
            layoutItem = itemView.findViewById(R.id.parent_layout_products);
            image = itemView.findViewById(R.id.image_product);
        }
    }
}