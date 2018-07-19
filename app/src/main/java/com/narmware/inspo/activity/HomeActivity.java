package com.narmware.inspo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.narmware.inspo.R;
import com.narmware.inspo.adapter.CardAdapter;
import com.narmware.inspo.fragment.GalleryFragment;
import com.narmware.inspo.fragment.HomeFragment;
import com.narmware.inspo.fragment.SingleImageFragment;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.support.CardStackView;
import com.narmware.inspo.support.OnSwipeTouchListener;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener,
        SingleImageFragment.OnFragmentInteractionListener,View.OnClickListener

{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Button mBtnSearch;
    CircleImageView mImgProf;
    ImageView mImgChat;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setFragment(new HomeFragment());
    }

    private void init() {

        mBtnSearch=findViewById(R.id.btn_search);
        mImgProf=findViewById(R.id.prof_img);
        mImgChat=findViewById(R.id.img_chat);

        mBtnSearch.setOnClickListener(this);
        mImgProf.setOnClickListener(this);
        mImgChat.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setFragment(Fragment fragment)
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_search:
                Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);

               // Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.prof_img:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.img_chat:
                Toast.makeText(this, "Meetups", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
