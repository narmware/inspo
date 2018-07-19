package com.narmware.inspo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.narmware.inspo.R;
import com.narmware.inspo.pojo.Category;
import com.narmware.inspo.pojo.SubCategory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

ArrayList<Category> categories;
Context mContext;
FragmentManager fragmentManager;

    public CategoryAdapter(ArrayList<Category> categories, Context mContext, FragmentManager fragmentManager) {
        this.categories = categories;
        this.mContext = mContext;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mTxtCatTitle;
        RecyclerView mSubRecycler;
        RequestQueue mVolleyRequest = Volley.newRequestQueue(mContext);
        ArrayList<SubCategory> subCategories=new ArrayList<>();
        SubCategoryAdapter subCategoryAdapter;
        Category mItem;

        public MyViewHolder(View itemView) {
            super(itemView);

           mTxtCatTitle=itemView.findViewById(R.id.txt_title);
           mSubRecycler=itemView.findViewById(R.id.recyclerView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.mItem = categories.get(position);

        holder.mTxtCatTitle.setText(category.getName());

        holder.subCategories.add(new SubCategory("Party Hub","Sweet Chariot","Saturday 4.00 PM","http://discoverkeighley.co.uk/assets/Party-Hub-12-SM-1510316892.jpg"));
        holder.subCategories.add(new SubCategory("Travel Club","Vaishali Hotel","Friday 8.00 PM","https://www.corporatetraveller.com.au/sites/default/files/2018-03/your-travel-club-HW.jpg"));
        holder.subCategories.add(new SubCategory("Happy Mates","Goodluck Cafe","Sunday 10.00 AM","http://gautamsawala.in/wp-content/uploads/2014/09/DSC_1001.jpg"));
        holder.subCategories.add(new SubCategory("Backpacker's","Sinhagad","Sunday 7.00 AM","https://www.backpackertravel.org/wp-content/uploads/2014/12/backpackers.jpg"));

        holder.subCategoryAdapter = new SubCategoryAdapter(holder.subCategories, mContext,fragmentManager);
        holder.mSubRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        holder.mSubRecycler.setItemAnimator(new DefaultItemAnimator());
        holder.mSubRecycler.setAdapter(holder.subCategoryAdapter);
        holder.mSubRecycler.setNestedScrollingEnabled(true);
        holder.mSubRecycler.setFocusable(false);

        holder.subCategoryAdapter.notifyDataSetChanged();

        /* if (holder.subCategories.size() == 0) {
            HashMap<String, String> param = new HashMap();
            param.put(Constants.PARENT_CATEGORY_ID, category.getId_category());

            //url with params
            String url = SupportFunctions.appendParam(MyApplication.SUB_CAT_URL, param);

            //url without params
            //String url= MyApplication.SERVER_URL;

            Log.e("Cat url", url);
            JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                    // The third parameter Listener overrides the method onResponse() and passes
                    //JSONObject as a parameter
                    new Response.Listener<JSONObject>() {

                        // Takes the response from the JSON request
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.e("SubCat Json_string", response.toString());
                                Gson gson = new Gson();

                                CategoryResponse categoryResponse = gson.fromJson(response.toString(), CategoryResponse.class);
                                Category[] cats = categoryResponse.getCategories();

                                for (Category item : cats) {

                                    Log.e("Parent id",item.getId_category_parent()+" : "+holder.mItem.getId_category());

                                    if(item.getId_category_parent().equals(holder.mItem.getId_category())) {
                                        holder.subCategories.add(item);
                                        Log.e("SubCat name: ", item.getName() + "  " + item.getId_category_parent());
                                    }
                                }
                                holder.subCategoryAdapter.notifyDataSetChanged();

                            } catch (Exception e) {

                                e.printStackTrace();

                            }
                        }
                    },
                    // The final parameter overrides the method onErrorResponse() and passes VolleyError
                    //as a parameter
                    new Response.ErrorListener() {
                        @Override
                        // Handles errors that occur due to Volley
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "Test Error");

                        }
                    }
            );
            holder.mVolleyRequest.add(obreq);
        }*/
    }

}
