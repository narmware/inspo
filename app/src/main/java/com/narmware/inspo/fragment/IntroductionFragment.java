package com.narmware.inspo.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.activity.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IntroductionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IntroductionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroductionFragment extends Fragment implements View.OnClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "title";
    private static final String ARG_IMAGE = "image";
    private static final String ARG_DESC = "desc";

    // TODO: Rename and change types of parameters
     String title,desc;
    int image;

    TextView mTxtTitle,mTxtDesc;
    ImageView mImg;
    Button mBtnSignIn,mBtnLogIn;
    public static LinearLayout mLinearSignIn;

    private OnFragmentInteractionListener mListener;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static IntroductionFragment newInstance(String title, String desc, int image) {
        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, desc);
        args.putInt(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            desc = getArguments().getString(ARG_DESC);
            image = getArguments().getInt(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_intro, container, false);

        init(view);
        return view;
    }

    private void init(View view) {

        mTxtTitle=view.findViewById(R.id.intro_title);
        mImg=view.findViewById(R.id.intro_img);
        mTxtDesc=view.findViewById(R.id.intro_desc);

        mLinearSignIn=view.findViewById(R.id.lin_signin);
        mBtnLogIn=view.findViewById(R.id.btn_login);
        mBtnSignIn=view.findViewById(R.id.btn_signup);

        mBtnSignIn.setOnClickListener(this);
        mBtnLogIn.setOnClickListener(this);

        mTxtTitle.setText(title);
        mTxtDesc.setText(desc);
        mImg.setImageResource(image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_signup:
                break;

            case R.id.btn_login:
                Intent intent=new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }

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
