package com.narmware.inspo.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.narmware.inspo.R;
import com.narmware.inspo.support.ImageBlur;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SingleImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SingleImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleImageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGE = "image";
    private static final String ARG_TITLE = "title";

    // TODO: Rename and change types of parameters
    private String mImage;
    private String mTitle;

    private OnFragmentInteractionListener mListener;

    TextView mTxtImgTitle;
    ImageView mImgBlurBg,mImgGallery;
    Bitmap bitmap;

    public SingleImageFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SingleImageFragment newInstance(String image, String title) {
        SingleImageFragment fragment = new SingleImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE, image);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getString(ARG_IMAGE);
            mTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_single_image, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        mImgBlurBg=view.findViewById(R.id.img_blur_bg);
        mImgGallery=view.findViewById(R.id.img_gallery);
        mTxtImgTitle=view.findViewById(R.id.txt_img_title);

        Picasso.with(getContext())
                .load(mImage)
                .into(mImgGallery);

        mTxtImgTitle.setText(mTitle);

        try{
            bitmap = new ImageBlur().getBitmapFromURL(mImage);
            mImgBlurBg.setImageBitmap(new ImageBlur().fastblur(bitmap, 12));
        }
        catch (Exception e)
        {}


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
