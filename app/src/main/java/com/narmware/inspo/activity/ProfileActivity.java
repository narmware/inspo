package com.narmware.inspo.activity;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.fragment.ProfileFragment;
import com.narmware.inspo.fragment.SelectSkillsFragment;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,ProfileFragment.OnFragmentInteractionListener
,SelectSkillsFragment.OnFragmentInteractionListener
{


    ImageButton mBtnBack;
    public static TextView mTxtTitle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //used to hide keyboard bydefault
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();
       /* InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);*/

       /* databaseAccess = DatabaseAccess.getInstance(ProfileActivity.this);
        databaseAccess.open();
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
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            //The array list has the image paths of the selected images

            temp=new ArrayList<>();
            temp.clear();
            temp=databaseAccess.getAllDetails();
            Log.e("TempData",temp.size()+"");
            if(temp.size() != 0)
            {


            }
            else
            {

                finish();
            }
            //temp = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            images=new ArrayList<>();
            images.clear();
            for(int i=0;i<temp.size();i++) {

               // Log.e("ImageData",temp.get(i).path);

                images.add(temp.get(i));

            }
            countSelected=images.size();
            Log.e("CountData",countSelected+"");

            galleryAdapter = new ProfileGalleryAdapter(images,ProfileActivity.this);
            profileRecycler.setAdapter(galleryAdapter);

            galleryAdapter.notifyDataSetChanged();
        }
    }*/
}
