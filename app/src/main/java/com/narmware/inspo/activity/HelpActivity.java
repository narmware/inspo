package com.narmware.inspo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.narmware.inspo.R;
import com.narmware.inspo.fragment.IntroductionFragment;


public class HelpActivity extends AppCompatActivity implements IntroductionFragment.OnFragmentInteractionListener{

    protected ViewPager mViewPager;
  protected Button mBtnSkip;

    PagerAdapter mAdapter;
    int pageCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().hide();
        init();
    }

    private void init() {

        mViewPager=findViewById(R.id.intro_pager);

        mAdapter=new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new DefaultTransformer());

        mBtnSkip=findViewById(R.id.btn_skip);

       /* mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = mViewPager.getCurrentItem() + 1;
                //Toast.makeText(HelpActivity.this, "Count: "+pageCount, Toast.LENGTH_SHORT).show();

                if (pageCount == 2)
                {
                    mBtnNext.setText("Get Started");
                }
                if (pageCount == 3)
                {
                    *//*Intent intent=new Intent(HelpActivity.this,OtpLoginActivity.class);
                    startActivity(intent);
                    finish();*//*
                }
                mViewPager.setCurrentItem(pageCount);
            }
        });*/

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //Toast.makeText(HelpActivity.this,"Page scrolled"+position,Toast.LENGTH_SHORT).show();

                if (position == 2)
                {
                    IntroductionFragment.mLinearSignIn.setVisibility(View.VISIBLE);
                    mBtnSkip.setVisibility(View.INVISIBLE);
                }
                else{

                    IntroductionFragment.mLinearSignIn.setVisibility(View.INVISIBLE);
                    mBtnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    return new IntroductionFragment().newInstance(getResources().getString(R.string.help1_title),getResources().getString(R.string.help1_text),R.drawable.ic_launcher_background);
                case 1:
                    return new IntroductionFragment().newInstance(getResources().getString(R.string.help2_title),getResources().getString(R.string.help2_text),R.drawable.ic_launcher_background);
                case 2:
                    return new IntroductionFragment().newInstance(getResources().getString(R.string.help3_title),getResources().getString(R.string.help3_text),R.drawable.ic_launcher_background);

            }
            return null;
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return 3;
        }
    }

}
