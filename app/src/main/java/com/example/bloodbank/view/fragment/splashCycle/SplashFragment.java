package com.example.bloodbank.view.fragment.splashCycle;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;

import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;
import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment {



    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //initFragment();
        View view= inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ReplaceFragment( new SilderFragment(),getActivity().getSupportFragmentManager(), R.id.main_activity_layout , null, null);
                /*getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_activity_layout , new SilderFragment()).commit();*/
            }

        }, 3000);

        return view;

    }




}
