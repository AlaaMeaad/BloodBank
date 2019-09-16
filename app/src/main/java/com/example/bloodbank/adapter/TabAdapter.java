package com.example.bloodbank.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.homeCycle.DonationRequestFragment;
import com.example.bloodbank.view.fragment.homeCycle.PostsFragment;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
    Context context;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentsTitle = new ArrayList<>();


    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addPage(Fragment fragment, String fragmentTitle) {
        fragments.add(fragment);
        fragmentsTitle.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}