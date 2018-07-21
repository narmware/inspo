package com.narmware.inspo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.narmware.inspo.R;
import com.narmware.inspo.activity.SearchActivity;
import com.narmware.inspo.adapter.OptionTagAdapter;
import com.narmware.inspo.adapter.SearchAdapter;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Tags;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import me.gujun.android.taggroup.TagGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectSkillsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectSkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectSkillsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static TagContainerLayout selectedOptionGroup;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ArrayList<Tags> tags;
    OptionTagAdapter optionTagAdapter;
    RecyclerView mRecyclerOption;

    public SelectSkillsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectSkillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectSkillsFragment newInstance(String param1, String param2) {
        SelectSkillsFragment fragment = new SelectSkillsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_select_skills, container, false);
        tags=new ArrayList<>();

        init(view);
        return view;

    }

    private void init(View view) {
        selectedOptionGroup=view.findViewById(R.id.selected_opt_group);
        mRecyclerOption=view.findViewById(R.id.option_recycler);

        setTagAdapter(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        selectedOptionGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {

                tags.add(new Tags(text,""));
                selectedOptionGroup.removeTag(position);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

                String text=selectedOptionGroup.getTagText(position);
                tags.add(new Tags(text,""));
                selectedOptionGroup.removeTag(position);

            }
        });
    }

    public void setTagAdapter(RecyclerView.LayoutManager mLayoutManager){
        SnapHelper snapHelper = new LinearSnapHelper();
        tags.add(new Tags("Leadership","1"));
        tags.add(new Tags("Management","1"));
        tags.add(new Tags("Collaboration","1"));
        tags.add(new Tags("Finance","1"));
        tags.add(new Tags("Blogging","1"));

        optionTagAdapter = new OptionTagAdapter(tags,getContext());
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(GalleryActivity.this,2);
        mRecyclerOption.setLayoutManager(mLayoutManager);
        mRecyclerOption.setItemAnimator(new DefaultItemAnimator());
        //snapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerOption.setAdapter(optionTagAdapter);
        mRecyclerOption.setNestedScrollingEnabled(false);
        mRecyclerOption.setFocusable(false);

        optionTagAdapter.notifyDataSetChanged();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
