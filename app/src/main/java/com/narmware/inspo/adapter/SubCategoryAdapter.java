package com.narmware.inspo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.pojo.Category;
import com.narmware.inspo.pojo.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>{

ArrayList<SubCategory> categories;
Context mContext;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public SubCategoryAdapter(ArrayList<SubCategory> categories, Context mContext, FragmentManager fragmentManager) {
        this.categories = categories;
        this.mContext = mContext;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.sub_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mTxtCatTitle,mTxtPlace,mTxtTime;
        ImageView mImgPlace;
        SubCategory mItem;

        public MyViewHolder(View view) {
            super(view);

           mTxtCatTitle=view.findViewById(R.id.txt_sub_title);
            mTxtPlace=view.findViewById(R.id.txt_place);
            mTxtTime=view.findViewById(R.id.txt_time);
            mImgPlace=view.findViewById(R.id.img_place);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(mContext,mItem.getName()+" : "+mItem.getId_category_parent(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SubCategory category=categories.get(position);

        holder.mTxtCatTitle.setText(category.getName());
        holder.mTxtPlace.setText(category.getPlace());
        holder.mTxtTime.setText(category.getTime());


        Picasso.with(mContext)
                .load(category.getImage())
                .fit()
                .into(holder.mImgPlace);

        holder.mItem=category;
    }

}
