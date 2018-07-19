package com.narmware.inspo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.narmware.inspo.R;
import com.narmware.inspo.pojo.GalleryItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ViewPager galleryPager;
    PagerAdapter pagerAdapter;
    ArrayList<GalleryItem> images;

    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
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
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        init(view);
        return view;
    }

    private void init(View view) {

        images=new ArrayList<>();

        images.add(new GalleryItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAwWs6KvTQEczjXUYwvBMXFfYf5MciLTsTSonSL_wwaS_QhT3oeA","My app1"));
        images.add(new GalleryItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdws49Da0blBR61fss9NfkEXr86ai-P4YdQtc9SHOq0Fpq5MS8aw","My app 2"));
        images.add(new GalleryItem("https://image.freepik.com/free-photo/young-hipster-traveling-backpacker-around-asia-holiday-tourist-concept_1150-1879.jpg","My Tour"));
        images.add(new GalleryItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdws49Da0blBR61fss9NfkEXr86ai-P4YdQtc9SHOq0Fpq5MS8aw","My app 4"));

        galleryPager=view.findViewById(R.id.gallery_pager);
        pagerAdapter=new PagerAdapter(getActivity().getSupportFragmentManager(),getContext());
        galleryPager.setPageTransformer(true, new DepthPageTransformer());
        galleryPager.setAdapter(pagerAdapter);

        for(int i=0;i<images.size();i++)
        {
            pagerAdapter.addFragment(SingleImageFragment.newInstance(images.get(i).getImage(),images.get(i).getTitle()));
        }
        pagerAdapter.notifyDataSetChanged();
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

    public class PagerAdapter extends FragmentStatePagerAdapter {

        Context context;
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public PagerAdapter(FragmentManager fm, Context mContext) {
            super(fm);
            this.context=mContext;
        }

        @Override
        public Fragment getItem(int index) {

            return mFragmentList.get(index);
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

}
