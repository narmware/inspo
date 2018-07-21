package com.narmware.inspo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.CategoryAdapter;
import com.narmware.inspo.pojo.Category;

import java.util.ArrayList;

public class MeetupActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;
    ImageButton mBtnBack;
    TextView mTxtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);
        getSupportActionBar().hide();

        init();

        setCatAdapter(new LinearLayoutManager(MeetupActivity.this));
    }

    private void init() {
        mBtnBack=findViewById(R.id.btn_back);
        recyclerView=findViewById(R.id.recyclerView);
        mTxtTitle=findViewById(R.id.txt_title);

        mTxtTitle.setText("Meetup");
        mBtnBack.setOnClickListener(this);
    }

    public void setCatAdapter(RecyclerView.LayoutManager mLayoutManager){
        categories=new ArrayList<>();
        SnapHelper snapHelper = new LinearSnapHelper();

        categories.add(new Category("Cat 1"));
        categories.add(new Category("Cat 2"));
        categories.add(new Category("Cat 3"));

        categoryAdapter = new CategoryAdapter(categories,MeetupActivity.this,getSupportFragmentManager());
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(GalleryActivity.this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //snapHelper.attachToRecyclerView(mRecyclerView);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
