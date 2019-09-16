package com.example.bloodbank.view.fragment.homeCycle;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.TabAdapter;
import com.example.bloodbank.helper.HelperMethod;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.fragment_home_vp_tab_home)
    ViewPager fragmentHomeVpTabHome;
    @BindView(R.id.fragment_home_tl_tab_home)
    TabLayout fragmentHomeTlTabHome;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        setupViewPager();
        ReplaceFragment(new PostsFragment(), getFragmentManager(), R.id.home_activity_layout, null, null);

        return v;
    }
    @SuppressLint({"ResourceType", "StringFormatInvalid"})
    private void setupViewPager() {
        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager());
        tabAdapter.addPage(new PostsFragment(), getString(R.string.articles));

        tabAdapter.addPage(new DonationRequestFragment(), getString(R.string.donation_requests));
        fragmentHomeVpTabHome.setAdapter(tabAdapter);
        fragmentHomeTlTabHome.setupWithViewPager(fragmentHomeVpTabHome);
    }






}
