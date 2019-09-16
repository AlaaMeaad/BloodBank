package com.example.bloodbank.view.actavity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodbank.view.fragment.BaseFragment;


public abstract class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superonBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
