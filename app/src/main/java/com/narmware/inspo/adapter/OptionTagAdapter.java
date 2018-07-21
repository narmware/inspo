package com.narmware.inspo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.fragment.SelectSkillsFragment;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Tags;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class OptionTagAdapter extends RecyclerView.Adapter<OptionTagAdapter.MyViewHolder>{

ArrayList<Tags> tags;
Context mContext;

    public OptionTagAdapter(ArrayList<Tags> tags, Context mContext) {
        this.tags = tags;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_option_tag, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mTxtName;
        Tags mItem;

        public MyViewHolder(View itemView) {
            super(itemView);

           mTxtName=itemView.findViewById(R.id.txt_option_tag);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position= (int) mTxtName.getTag();

                   SelectSkillsFragment.selectedOptionGroup.addTag(mItem.getName());
                   tags.remove(position);
                   notifyDataSetChanged();
               }

           });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Tags singleResult = tags.get(position);
        holder.mItem = tags.get(position);
        holder.mTxtName.setTag(position);
        holder.mTxtName.setText(singleResult.getName());
    }

}
