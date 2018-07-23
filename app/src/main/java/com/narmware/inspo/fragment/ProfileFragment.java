package com.narmware.inspo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.activity.ProfileActivity;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.Constants;
import com.narmware.inspo.support.DatabaseAccess;
import com.narmware.inspo.support.SharedPreferencesHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static ArrayList<Image> images;
    public static List<Image> temp;
    GridView profileRecycler;
    ProfileGalleryAdapter galleryAdapter;
    TextView mTxtICanHelp,mTxtLookingFor,mTxtSkills;
    public static CircleImageView mImgProf;
    ImageButton mImgBtnEditProf;
    String userImage;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static TagContainerLayout helpGroup,lookingforGroup,skillsGroup;

    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);

        return view;

    }

    private void init(View view) {

        mTxtICanHelp=view.findViewById(R.id.txt_iCanHelp);
        mTxtLookingFor=view.findViewById(R.id.txt_lookingFor);
        mTxtSkills=view.findViewById(R.id.txt_skills);
        mImgBtnEditProf=view.findViewById(R.id.img_edit_prof);
        helpGroup=view.findViewById(R.id.help_group);
        lookingforGroup=view.findViewById(R.id.looking_for_group);
        skillsGroup=view.findViewById(R.id.skills_group);
        mImgProf=view.findViewById(R.id.prof_img);

        helpGroup.addTag("Sample tag");
        lookingforGroup.addTag("Sample tag");
        skillsGroup.addTag("Sample tag");

        mTxtICanHelp.setOnClickListener(this);
        mTxtLookingFor.setOnClickListener(this);
        mTxtSkills.setOnClickListener(this);
        mImgBtnEditProf.setOnClickListener(this);
    }

    public void setFragment(Fragment fragment)
    {
        fragmentManager=getActivity().getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.txt_iCanHelp:
                setFragment(SelectSkillsFragment.newInstance(Constants.HELP_WITH));
                break;

            case R.id.txt_lookingFor:
                setFragment(SelectSkillsFragment.newInstance(Constants.LOOKING_FOR));
                break;

            case R.id.txt_skills:
                setFragment(SelectSkillsFragment.newInstance(Constants.SKILLS));
                break;

            case R.id.img_edit_prof:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Constants.REQUEST_CODE);
                break;
        }
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
