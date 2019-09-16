package com.example.bloodbank.view.actavity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.splashCycle.SplashFragment;
import com.example.bloodbank.view.fragment.userCycle.LoginFragment;

import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        ReplaceFragment(new LoginFragment(),getSupportFragmentManager(),  R.id.user_cycle_lu, null, null);

    }
}
