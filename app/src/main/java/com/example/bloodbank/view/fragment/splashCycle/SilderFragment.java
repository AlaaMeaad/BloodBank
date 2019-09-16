package com.example.bloodbank.view.fragment.splashCycle;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SlidingImageAdapter;
import com.example.bloodbank.data.model.silderImage.SilderImageModel;
import com.example.bloodbank.view.actavity.UserCycleActivity;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SilderFragment extends BaseFragment {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    @BindView(R.id.slider_fragment_indicator)
    CirclePageIndicator sliderFragmentIndicator;
    @BindView(R.id.Silder_Fragment_btm_skip)
    Button SilderFragmentBtmSkip;
    @BindView(R.id.slider_fragment_pager)
    androidx.viewpager.widget.ViewPager sliderFragmentPager;
    private ArrayList<SilderImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.silder1, R.drawable.silder2};

    public SilderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // initFragment();

        View view = inflater.inflate(R.layout.fragment_silder, container, false);
        ButterKnife.bind(this, view);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();


        return view;

    }

    private ArrayList<SilderImageModel> populateList() {

        ArrayList<SilderImageModel> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            SilderImageModel imageModel = new SilderImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        SlidingImageAdapter slidingImageAdapter = new SlidingImageAdapter(getActivity() , imageModelArrayList);
        sliderFragmentPager.setAdapter(slidingImageAdapter);
        //sliderFragmentPager.setAdapter(new SlidingImageAdapter(getActivity(), imageModelArrayList));


        sliderFragmentIndicator.setViewPager(sliderFragmentPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        sliderFragmentIndicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                sliderFragmentPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        sliderFragmentIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }





    @OnClick({R.id.slider_fragment_indicator, R.id.Silder_Fragment_btm_skip})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.slider_fragment_pager:
                break;
            case R.id.slider_fragment_indicator:
                break;
            case R.id.Silder_Fragment_btm_skip:
                Intent i = new Intent(getActivity(), UserCycleActivity.class);
                getActivity().startActivity(i);
                break;

        }
    }
}
