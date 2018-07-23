package com.narmware.inspo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Image;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.MyViewHolder>{

ArrayList<Image> images;
Context mContext;

    public PortfolioAdapter(ArrayList<Image> images, Context mContext) {
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.portfolio_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImg;

        Image mItem;

        public MyViewHolder(View itemView) {
            super(itemView);

           mImg=itemView.findViewById(R.id.img);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(mContext, "id: "+mItem.name, Toast.LENGTH_SHORT).show();
               }
           });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Image singleResult = images.get(position);
        holder.mItem = images.get(position);

        File imgFile = new File(singleResult.path);
        if(imgFile.exists()){

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize=8;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inDither = true;

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
                holder.mImg.setImageBitmap(myBitmap);

            }catch (Exception e)
            {

            }
    }

}
}
