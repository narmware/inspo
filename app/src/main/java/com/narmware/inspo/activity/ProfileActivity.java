package com.narmware.inspo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.fragment.ProfileFragment;
import com.narmware.inspo.fragment.SelectSkillsFragment;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.Constants;
import com.narmware.inspo.support.DatabaseAccess;
import com.narmware.inspo.support.SharedPreferencesHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,ProfileFragment.OnFragmentInteractionListener
,SelectSkillsFragment.OnFragmentInteractionListener,SelectPortfolioFragment.OnFragmentInteractionListener
{


    ImageButton mBtnBack;
    Button mBtnNext;
    public static TextView mTxtTitle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //used to hide keyboard bydefault
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();
        databaseAccess = DatabaseAccess.getInstance(ProfileActivity.this);
        databaseAccess.open();
       /* InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);*/

       /*
        databaseAccess.deleteAll();

        profileRecycler=findViewById(R.id.prof_grid);

        Intent intent = new Intent(this, AlbumSelectActivity.class);
        //set limit on number of images that can be selected, default is 10
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, numOfImgToSelect);
        intent.putExtra("count", countSelected);
        startActivityForResult(intent, Constants.REQUEST_CODE);
*/
    }

    private void init() {
        mBtnBack=findViewById(R.id.btn_back);
        mBtnNext=findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
        mTxtTitle=findViewById(R.id.txt_title);
        mTxtTitle.setText("Me");

        mBtnBack.setOnClickListener(this);
        setFragment(new ProfileFragment());
    }

    public void setFragment(Fragment fragment)
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_back:
                onBackPressed();
                break;


            case R.id.btn_next:
                setFragment(new SelectPortfolioFragment());
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            //The array list has the image paths of the selected images

            SelectPortfolioFragment.temp=new ArrayList<>();
            SelectPortfolioFragment.temp.clear();
            SelectPortfolioFragment.temp=databaseAccess.getAllDetails();
            Log.e("TempData",SelectPortfolioFragment.temp.size()+"");
            if(SelectPortfolioFragment.temp.size() != 0)
            {


            }
            else
            {

                finish();
            }
            //temp = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            SelectPortfolioFragment.images=new ArrayList<>();
            SelectPortfolioFragment.images.clear();
            for(int i=0;i<SelectPortfolioFragment.temp.size();i++) {

                Log.e("ImageData",SelectPortfolioFragment.temp.get(i).path);

                //images.add(temp.get(i));

            }
          /*  countSelected=images.size();
            Log.e("CountData",countSelected+"");

            galleryAdapter = new ProfileGalleryAdapter(images,ProfileActivity.this);
            profileRecycler.setAdapter(galleryAdapter);

            galleryAdapter.notifyDataSetChanged();*/
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                SharedPreferencesHelper.setUserProfImg(imageUri.toString(),ProfileActivity.this);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Log.e("UserProfileImage",selectedImage+"");

                ProfileFragment.mImgProf.setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ProfileActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }*/
}
