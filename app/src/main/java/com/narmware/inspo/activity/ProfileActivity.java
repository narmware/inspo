package com.narmware.inspo.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.fragment.ProfileFragment;
import com.narmware.inspo.fragment.SelectSkillsFragment;
import com.narmware.inspo.support.Constants;

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
