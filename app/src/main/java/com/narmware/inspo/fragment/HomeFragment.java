package com.narmware.inspo.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.narmware.inspo.R;
import com.narmware.inspo.adapter.CardAdapter;
import com.narmware.inspo.pojo.CardItem;
import com.narmware.inspo.support.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<CardItem> cardItems;
    CardAdapter cardAdapter;
    public static CardStackView cardStackView;
    Button mBtnPass,mBtnMeet;
    RelativeLayout mRelEmptyCards,mRelButtons;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        mRelEmptyCards=view.findViewById(R.id.lin_empty_cards);
        mRelButtons=view.findViewById(R.id.relative_button);

        cardStackView=view.findViewById(R.id.card_stack);

        mBtnPass=view.findViewById(R.id.btn_pass);
        mBtnMeet=view.findViewById(R.id.btn_meet);

        List<String> intrestList=new ArrayList<>();
        intrestList.add("# Business");
        intrestList.add("# Leadership");
        intrestList.add("# Management");

        List<String> offersList=new ArrayList<>();
        offersList.add("# Leadership");
        offersList.add("# Management");

        List<String> looksList=new ArrayList<>();
        looksList.add("# Collaboration");
        looksList.add("# Finance");
        looksList.add("# Blogging");

        cardItems = new ArrayList<>();
        cardItems.add(new CardItem("1","Vrushali Varne","Android Developer","Pune,India","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAwWs6KvTQEczjXUYwvBMXFfYf5MciLTsTSonSL_wwaS_QhT3oeA",intrestList,offersList,looksList));
        cardItems.add(new CardItem("2","Nagesh Penidyal","Java Developer","Pune,India","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjQz7pKgq5klBH6RLAS12hhMcw8uI1a52mylZsxFnIf-QyDoOe",intrestList,offersList,looksList));
        cardItems.add(new CardItem("3","Rohit Savant","BDO","Hydrabad,India","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgzuaHIHuTTO_vX6EB_bOYSZ4CnMZ1bfafB_CvfSSHSCttdyup9A",intrestList,offersList,looksList));
        cardItems.add(new CardItem("4","Pooja Patil","PHP Developer","Mumbai,India","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6mdDFmTsu4ct_AGK2gF7aAUJ9qv6R_lZBuswwRHxRRAmx5oBzwA",intrestList,offersList,looksList));
        cardItems.add(new CardItem("5","Suraj Yadav","PHP Developer","Banglore,India","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdws49Da0blBR61fss9NfkEXr86ai-P4YdQtc9SHOq0Fpq5MS8aw",intrestList,offersList,looksList));

        cardAdapter=new CardAdapter(cardItems,getContext(),getActivity().getSupportFragmentManager());

        cardStackView.setAdapter(cardAdapter);
        cardStackView.setVisibleCount(cardItems.size());
        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.e("Dragging",percentX+"  "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                //Toast.makeText(HomeActivity.this, "id : "+cardItems.get(cardStackView.getTopIndex()-1).getId(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(HomeActivity.this, "Swiped", Toast.LENGTH_SHORT).show();
                if(cardItems.size()==cardStackView.getTopIndex())
                {

                    mRelEmptyCards.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight)
                            .duration(400)
                            .playOn(mRelEmptyCards);

                    mRelButtons.setVisibility(View.INVISIBLE);

                    YoYo.with(Techniques.SlideOutLeft)
                            .duration(400)
                            .playOn(mRelButtons);

                    //Toast.makeText(HomeActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCardReversed() {
            }

            @Override
            public void onCardMovedToOrigin() {
            }

            @Override
            public void onCardClicked(int index) {

            }
        });
        cardAdapter.notifyDataSetChanged();

        mBtnMeet.setOnClickListener(this);
        mBtnPass.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_meet:
                swipeRight();
                break;

            case R.id.btn_pass:
                swipeLeft();
                break;
        }
    }

    public void swipeLeft() {

        View target = cardStackView.getTopView();
        View targetOverlay = cardStackView.getTopView().getOverlayContainer();

        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", -10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet cardAnimationSet = new AnimatorSet();
        cardAnimationSet.playTogether(rotation, translateX, translateY);

        ObjectAnimator overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f);
        overlayAnimator.setDuration(200);
        AnimatorSet overlayAnimationSet = new AnimatorSet();
        overlayAnimationSet.playTogether(overlayAnimator);

        cardStackView.swipe(SwipeDirection.Left, cardAnimationSet, overlayAnimationSet);
    }

    public void swipeRight() {
        View target = cardStackView.getTopView();
        View targetOverlay = cardStackView.getTopView().getOverlayContainer();

        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet cardAnimationSet = new AnimatorSet();
        cardAnimationSet.playTogether(rotation, translateX, translateY);

        ObjectAnimator overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f);
        overlayAnimator.setDuration(200);
        AnimatorSet overlayAnimationSet = new AnimatorSet();
        overlayAnimationSet.playTogether(overlayAnimator);

        cardStackView.swipe(SwipeDirection.Right, cardAnimationSet, overlayAnimationSet);
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
