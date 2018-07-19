package com.narmware.inspo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.CategoryAdapter;
import com.narmware.inspo.pojo.Category;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getSupportActionBar().hide();

        recyclerView=findViewById(R.id.recyclerView);

            setCatAdapter(new LinearLayoutManager(DemoActivity.this));
    }

    public void setCatAdapter(RecyclerView.LayoutManager mLayoutManager){
        categories=new ArrayList<>();
        SnapHelper snapHelper = new LinearSnapHelper();

        categories.add(new Category("Cat 1"));
        categories.add(new Category("Cat 2"));
        categories.add(new Category("Cat 3"));

        categoryAdapter = new CategoryAdapter(categories,DemoActivity.this,getSupportFragmentManager());
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(GalleryActivity.this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //snapHelper.attachToRecyclerView(mRecyclerView);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

        categoryAdapter.notifyDataSetChanged();

    }
}
