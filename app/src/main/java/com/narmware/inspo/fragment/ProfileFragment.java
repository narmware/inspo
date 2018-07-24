package com.narmware.inspo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.activity.PortfolioActivity;
import com.narmware.inspo.activity.SearchActivity;
import com.narmware.inspo.adapter.PortfolioAdapter;
import com.narmware.inspo.adapter.SearchAdapter;
import com.narmware.inspo.adapter.galleryAdapter.ProfileGalleryAdapter;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.pojo.Image;
import com.narmware.inspo.support.Constants;
import com.narmware.inspo.support.DatabaseAccess;
import com.narmware.inspo.support.SharedPreferencesHelper;

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

    TextView mTxtICanHelp,mTxtLookingFor,mTxtSkills,mTxtPortfolio;
    public static CircleImageView mImgProf;
    ImageButton mImgBtnEditProf;
    String userImage;
    Button mBtnNext;
    public static  RecyclerView mRecyclerPortfolio;
   public static PortfolioAdapter portfolioAdpater;
    public static ArrayList<Image> images;
    public static DatabaseAccess databaseAccess;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static TagContainerLayout helpGroup,lookingforGroup,skillsGroup;
    public static Context context;
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
        context=getContext();
        return view;

    }

    private void init(View view) {

        databaseAccess= DatabaseAccess.getInstance(getContext());
        databaseAccess.open();

        mTxtICanHelp=view.findViewById(R.id.txt_iCanHelp);
        mTxtLookingFor=view.findViewById(R.id.txt_lookingFor);
        mTxtSkills=view.findViewById(R.id.txt_skills);
        mTxtPortfolio=view.findViewById(R.id.txt_portfolio);

        mImgBtnEditProf=view.findViewById(R.id.img_edit_prof);
        helpGroup=view.findViewById(R.id.help_group);
        lookingforGroup=view.findViewById(R.id.looking_for_group);
        skillsGroup=view.findViewById(R.id.skills_group);
        mImgProf=view.findViewById(R.id.prof_img);
        mRecyclerPortfolio=view.findViewById(R.id.portfolio_recycler);
        mBtnNext=view.findViewById(R.id.btn_next);

       // helpGroup.addTag("Sample tag");
        //lookingforGroup.addTag("Sample tag");
        //skillsGroup.addTag("Sample tag");

        mTxtICanHelp.setOnClickListener(this);
        mTxtLookingFor.setOnClickListener(this);
        mTxtSkills.setOnClickListener(this);
        mTxtPortfolio.setOnClickListener(this);
        mImgBtnEditProf.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        if(SharedPreferencesHelper.getUserProfImg(getContext())!=null) {
            Bitmap bitmap = StringToBitMap(SharedPreferencesHelper.getUserProfImg(getContext()));
            mImgProf.setImageBitmap(bitmap);
        }
        images=new ArrayList<>();

        List<Image> imageList=databaseAccess.getAllDetails();

        if(imageList.size()!=0) {
            for (int i = 0; i < imageList.size(); i++) {
                images.add(imageList.get(i));
            }

            setPortfolioAdapter(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setFragment(Fragment fragment)
    {
        fragmentManager=getActivity().getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void setPortfolioAdapter(RecyclerView.LayoutManager mLayoutManager){

        SnapHelper snapHelper = new LinearSnapHelper();

        portfolioAdpater = new PortfolioAdapter(images,context);
        mRecyclerPortfolio.setLayoutManager(mLayoutManager);
        mRecyclerPortfolio.setItemAnimator(new DefaultItemAnimator());
       // snapHelper.attachToRecyclerView(mRecyclerPortfolio);
        mRecyclerPortfolio.setAdapter(portfolioAdpater);
        mRecyclerPortfolio.setNestedScrollingEnabled(false);
        mRecyclerPortfolio.setFocusable(false);

        portfolioAdpater.notifyDataSetChanged();

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
                SharedPreferencesHelper.setSelectionFlag(Constants.HELP_WITH,getContext());
                setFragment(new SelectSkillsFragment());
                break;

            case R.id.txt_lookingFor:
                SharedPreferencesHelper.setSelectionFlag(Constants.LOOKING_FOR,getContext());
                setFragment(new SelectSkillsFragment());
                break;

            case R.id.txt_skills:
                SharedPreferencesHelper.setSelectionFlag(Constants.SKILLS,getContext());
                setFragment(new SelectSkillsFragment());
                break;

            case R.id.txt_portfolio:
                Intent intent=new Intent(getContext(), PortfolioActivity.class);
                startActivity(intent);
                break;

            case R.id.img_edit_prof:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Constants.REQUEST_CODE);
                break;

            case R.id.btn_next:

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
