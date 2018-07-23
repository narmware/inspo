package com.narmware.inspo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.narmware.inspo.R;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.gallery_activities.AlbumSelectActivity;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.Constants;
import com.narmware.inspo.support.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectPortfolioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectPortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectPortfolioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView mPortfolioRecycler;
    DatabaseAccess databaseAccess;
    int numOfImgToSelect=8;
    public static int countSelected;
    public static ArrayList<Image> images;
    public static List<Image> temp;
    public static ProfileGalleryAdapter galleryAdapter;

    private OnFragmentInteractionListener mListener;

    public SelectPortfolioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectPortfolioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectPortfolioFragment newInstance(String param1, String param2) {
        SelectPortfolioFragment fragment = new SelectPortfolioFragment();
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
        View view= inflater.inflate(R.layout.fragment_select_portfolio, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        mPortfolioRecycler=view.findViewById(R.id.portfolio_recycler);
        temp=new ArrayList<>();
        images=new ArrayList<>();

        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        databaseAccess.deleteAll();

        Intent intent = new Intent(getContext(), AlbumSelectActivity.class);
        //set limit on number of images that can be selected, default is 10
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, numOfImgToSelect);
        intent.putExtra("count", countSelected);
        startActivityForResult(intent, Constants.REQUEST_CODE);

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
