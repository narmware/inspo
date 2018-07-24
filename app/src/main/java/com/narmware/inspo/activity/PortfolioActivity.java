package com.narmware.inspo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.fragment.ProfileFragment;
import com.narmware.inspo.gallery_activities.AlbumSelectActivity;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.Constants;
import com.narmware.inspo.support.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

public class PortfolioActivity extends AppCompatActivity implements View.OnClickListener{

    int countSelected=0;
    int numberOfSelection=8;
    DatabaseAccess databaseAccess;
    public static ArrayList<Image> images;
    public static List<Image> temp;
    GridView portfolioRecycler;
    ProfileGalleryAdapter portfolioAdpater;
    Button mBtnSelect,mBtnDone;
    ImageButton mBtnBack;
    TextView mTxtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        getSupportActionBar().hide();

        portfolioRecycler=findViewById(R.id.grid_portfolio);
        mBtnDone=findViewById(R.id.btn_done);
        mBtnDone.setVisibility(View.VISIBLE);
        mBtnBack=findViewById(R.id.btn_back);
        mBtnSelect=findViewById(R.id.btn_select);

        mBtnBack.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

        databaseAccess = DatabaseAccess.getInstance(PortfolioActivity.this);
        databaseAccess.open();

        temp=new ArrayList<>();
        images=new ArrayList<>();

        temp=databaseAccess.getAllDetails();
        if(temp.size()!=0) {
            for (int i = 0; i < temp.size(); i++) {

                Log.e("ImageData", temp.get(i).path);
                images.add(temp.get(i));

            }
        }
        
        portfolioAdpater = new ProfileGalleryAdapter(images,PortfolioActivity.this);
        portfolioRecycler.setAdapter(portfolioAdpater);
        portfolioAdpater.notifyDataSetChanged();

        portfolioRecycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                images.remove(i);
                portfolioAdpater.notifyDataSetChanged();
                databaseAccess.deleteSingle(images.get(i).id,images.get(i).album);
                Toast.makeText(PortfolioActivity.this, images.get(i).id+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Toast.makeText(this, requestCode+"   "+requestCode, Toast.LENGTH_SHORT).show();

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

                Log.e("ImageData",temp.get(i).path);
                images.add(temp.get(i));

            }
            countSelected=images.size();
            Log.e("CountData",countSelected+"");

            portfolioAdpater = new ProfileGalleryAdapter(images,PortfolioActivity.this);
            portfolioRecycler.setAdapter(portfolioAdpater);
            portfolioAdpater.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_select:
                Intent intent = new Intent(PortfolioActivity.this, AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, numberOfSelection);
                intent.putExtra("count", countSelected);
                startActivityForResult(intent, Constants.REQUEST_CODE);
                break;

            case R.id.btn_back:
                onBackPressed();
                break;

            case R.id.btn_done:
                mBtnDone.setVisibility(View.INVISIBLE);
                onBackPressed();
                ProfileFragment.setPortfolioAdapter(new LinearLayoutManager(PortfolioActivity.this,LinearLayoutManager.HORIZONTAL,false));

                break;
        }
    }
}
