package com.example.bloodbank.view.actavity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.splashCycle.SplashFragment;

import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;

public class SplashAndSilderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashandsilder);


        ReplaceFragment(new SplashFragment(),getSupportFragmentManager(),  R.id.main_activity_layout, null, null);

    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
