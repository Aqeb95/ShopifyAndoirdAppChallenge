package com.example.aqeb_.shopifyapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagsListAdapter extends RecyclerView.Adapter<TagsListAdapter.ViewHolder> {

    private Context context;
    private List<String> sortedTags;
    private List<Products> products;
    Typeface typeface;
    TextView title;
    View view;

    public TagsListAdapter(Context context, List<String> sortedTags, List<Products> products) {
        this.context = context;
        this.sortedTags = sortedTags;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        setFont();
        return holder;
    }

    private void setFont(){
        typeface = Typeface.createFromAsset(context.getAssets(), "caviardreams_bold.ttf");
        title = view.findViewById(R.id.text_tags);
        title.setTypeface(typeface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(sortedTags.get(position));
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("Title",sortedTags.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sortedTags.size();
    }

    public void filteredList(ArrayList<String> filteredList){
        sortedTags = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RelativeLayout layoutItem;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_tags);
            layoutItem = itemView.findViewById(R.id.parent_layout);

        }
    }
}
