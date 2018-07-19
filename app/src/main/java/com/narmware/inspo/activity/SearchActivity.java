package com.narmware.inspo.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.CategoryAdapter;
import com.narmware.inspo.adapter.SearchAdapter;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Category;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton mBtnBack,mBtnClose;
    EditText mEdtSearch;
    String mSearchText;
    ArrayList<CardItem> results;
    SearchAdapter searchAdapter;
    RecyclerView mSearchRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().hide();

        init();

    }

    private void init() {
       /* InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/

        mBtnBack=findViewById(R.id.btn_back);
        mBtnClose=findViewById(R.id.btn_close);
        mEdtSearch=findViewById(R.id.edt_search);
        mSearchRecycler=findViewById(R.id.search_recycler);

        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchText=mEdtSearch.getText().toString();

                if(mSearchText.equals("")) {
                    mBtnClose.setVisibility(View.INVISIBLE);
                }else {

                    if(mSearchText.length() > 2)
                    {
                        setSearchResultAdapter(new LinearLayoutManager(SearchActivity.this));
                    }
                    mBtnClose.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtnClose.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }
    public void setSearchResultAdapter(RecyclerView.LayoutManager mLayoutManager){
        results=new ArrayList<>();
        SnapHelper snapHelper = new LinearSnapHelper();

        results.add(new CardItem("1","Vrushali Varne","","Working at Narmware","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAwWs6KvTQEczjXUYwvBMXFfYf5MciLTsTSonSL_wwaS_QhT3oeA",null,null,null));
        results.add(new CardItem("2","Rohit Savant","","Working at Narmware","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgzuaHIHuTTO_vX6EB_bOYSZ4CnMZ1bfafB_CvfSSHSCttdyup9A",null,null,null));

        searchAdapter = new SearchAdapter(results,SearchActivity.this,getSupportFragmentManager());
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(GalleryActivity.this,2);
        mSearchRecycler.setLayoutManager(mLayoutManager);
        mSearchRecycler.setItemAnimator(new DefaultItemAnimator());
        //snapHelper.attachToRecyclerView(mRecyclerView);
        mSearchRecycler.setAdapter(searchAdapter);
        mSearchRecycler.setNestedScrollingEnabled(false);
        mSearchRecycler.setFocusable(false);

        searchAdapter.notifyDataSetChanged();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_back:
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                finish();
                break;

            case R.id.btn_close:
                mEdtSearch.setText("");
                break;
        }
    }
}
