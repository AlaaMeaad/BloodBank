package com.example.bloodbank.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.view.actavity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void initFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.superonBackPressed();
    }

}
