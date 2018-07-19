package com.narmware.inspo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.narmware.inspo.R;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Category;
import com.narmware.inspo.pojo.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

ArrayList<CardItem> results;
Context mContext;
FragmentManager fragmentManager;

    public SearchAdapter(ArrayList<CardItem> results, Context mContext, FragmentManager fragmentManager) {
        this.results = results;
        this.mContext = mContext;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mTxtName,mTxtLocation;
        ImageView mImg;

        CardItem mItem;

        public MyViewHolder(View itemView) {
            super(itemView);

           mTxtName=itemView.findViewById(R.id.txt_res_name);
           mTxtLocation=itemView.findViewById(R.id.txt_res_workplace);
           mImg=itemView.findViewById(R.id.res_img);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(mContext, "id: "+mItem.getId(), Toast.LENGTH_SHORT).show();
               }
           });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final CardItem singleResult = results.get(position);
        holder.mItem = results.get(position);

        holder.mTxtName.setText(singleResult.getName());
        holder.mTxtLocation.setText(singleResult.getLocation());

        Picasso.with(mContext)
                .load(singleResult.getImage())
                .fit()
                .into(holder.mImg);
    }

}
