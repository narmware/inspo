package com.narmware.inspo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.fragment.GalleryFragment;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.support.ImageBlur;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by rohitsavant on 03/07/18.
 */

public class CardAdapter extends BaseAdapter {

    ArrayList<CardItem> cardItems;
    Context mContext;
    Bitmap bitmap;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public CardAdapter(ArrayList<CardItem> cardItems, Context mContext, FragmentManager fragmentManager) {
        this.cardItems = cardItems;
        this.mContext = mContext;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public int getCount() {
        return cardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        TextView mTxtName=itemView.findViewById(R.id.prof_name);
        mTxtName.setText(cardItems.get(position).getName());

        TextView mTxtLoc=itemView.findViewById(R.id.prof_loc);
        mTxtLoc.setText(cardItems.get(position).getLocation());

        TextView mTxtOccu=itemView.findViewById(R.id.prof_occu);
        mTxtOccu.setText(cardItems.get(position).getOccupation());

        TagGroup mOfferGroup = (TagGroup) itemView.findViewById(R.id.offer_group);
        List<String> offers=cardItems.get(position).getOffersList();
        mOfferGroup.setTags(offers);

        TagGroup mLookGroup = (TagGroup) itemView.findViewById(R.id.look_group);
        List<String> looks=cardItems.get(position).getLooksList();
        mLookGroup.setTags(looks);

        TagGroup mIntrestGroup = (TagGroup) itemView.findViewById(R.id.intrest_group);
        List<String> intrests=cardItems.get(position).getIntrestList();
        mIntrestGroup.setTags(intrests);

        ImageView mImgProf=itemView.findViewById(R.id.prof_img);
        Picasso.with(mContext)
                .load(cardItems.get(position).getImage())
                .into(mImgProf);

        ImageView mImgBack=itemView.findViewById(R.id.img_background);

        try {
        bitmap = new ImageBlur().getBitmapFromURL(cardItems.get(position).getImage());
            mImgBack.setImageBitmap(new ImageBlur().fastblur(bitmap, 12));
        }catch (Exception e)
        {

        }

        RelativeLayout clickableRelative=itemView.findViewById(R.id.relative_clickable);
        clickableRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new GalleryFragment());
                //Toast.makeText(mContext, cardItems.get(position).getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
        return itemView;
    }


    public void setFragment(Fragment fragment)
    {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
