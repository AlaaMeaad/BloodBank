package com.example.bloodbank.view.fragment.userCycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiServers;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.helper.ShrdPreferences;
import com.example.bloodbank.view.actavity.HomeActivity;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.userCycle.forgetPassword.ForgetPasswordStep1Fragment;
import com.example.bloodbank.view.fragment.userCycle.newAccount.RegisterFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.BloodConstants.API_TOKEN;
import static com.example.bloodbank.helper.BloodConstants.PASSWORD;
import static com.example.bloodbank.helper.BloodConstants.PHONE;
import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;
import static com.example.bloodbank.helper.HelperMethod.dismissProgressDialog;
import static com.example.bloodbank.helper.HelperMethod.showProgressDialog;
import static com.example.bloodbank.helper.ShrdPreferences.LoadBoolean;
import static com.example.bloodbank.helper.ShrdPreferences.LoadStringData;
import static com.example.bloodbank.helper.ShrdPreferences.SaveData;
import static com.example.bloodbank.helper.ShrdPreferences.clean;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    @BindView(R.id.login_fargment_tx_phone)
    TextInputLayout loginFargmentTxPhone;
    @BindView(R.id.login_fargment_tx_password)
    TextInputLayout loginFargmentTxPassword;
    @BindView(R.id.login_fargment_chbox)
    CheckBox loginFargmentChbox;
    @BindView(R.id.user_tv_forget_password)
    TextView userTvForgetPassword;
    @BindView(R.id.login_but_login)
    Button loginButLogin;
    @BindView(R.id.login_but_creat)
    Button loginButCreat;
    @BindView(R.id.appedit1)
    AppCompatEditText appedit1;
    @BindView(R.id.appedit2)
    AppCompatEditText appedit2;
    @BindView(R.id.login_fargment_lu)
    RelativeLayout loginFargmentLu;
    ApiServers apiServers;
    static String KEY_FORGET_CHECK = "isChecked";
    private boolean isChecked;
    private Unbinder bind;

    public LoginFragment() {
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
        initFragment();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        bind = ButterKnife.bind(this, view);

        apiServers = getClient().create(ApiServers.class);
        rememberUser();

        loginFargmentChbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChecked = b;
            }
        });
        return view;
    }

    private void rememberUser() {
        LoadBoolean(getActivity(), KEY_FORGET_CHECK, loginFargmentChbox.isChecked());
        if (LoadBoolean(getActivity(), KEY_FORGET_CHECK, true)) {
            loginFargmentChbox.setChecked(true);
            loginFargmentTxPhone.getEditText().setText(LoadStringData(getActivity(), PHONE));
            loginFargmentTxPassword.getEditText().setText(LoadStringData(getActivity(), PASSWORD));

        }
        ShrdPreferences.SaveData(getActivity(), KEY_FORGET_CHECK, loginFargmentChbox.isChecked());

    }

    private void getAllFileds() {
        String phone = loginFargmentTxPhone.getEditText().getText().toString();
        String password = loginFargmentTxPassword.getEditText().getText().toString();
        login(phone, password);

    }

    private void login(String phone, String password) {
        showProgressDialog(getActivity(), "Loading");
        apiServers.loginBloodBank(phone, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                dismissProgressDialog();
                try {

                    if (response.body().getStatus() == 1) {
                        String phone1 = response.body().getDataLogin().getClient().getPhone();
                        String apiToken = response.body().getDataLogin().getApiToken();
                        String email = response.body().getDataLogin().getClient().getEmail();
                        String name = response.body().getDataLogin().getClient().getName();
                        String birthDate = response.body().getDataLogin().getClient().getBirthDate();
                        String donationLastDate = response.body().getDataLogin().getClient().getDonationLastDate();

                        saveData(apiToken, phone, password);
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getActivity(), HomeActivity.class);
                        getActivity().startActivity(i);
                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @OnClick({R.id.login_but_login, R.id.login_but_creat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_but_login:
                getAllFileds();

                break;
            case R.id.login_but_creat:
                ReplaceFragment(new RegisterFragment(), getActivity().getSupportFragmentManager(), R.id.user_cycle_lu, null, null);

                break;
        }
    }

    @OnClick(R.id.user_tv_forget_password)
    public void onViewClicked() {

        ReplaceFragment(new ForgetPasswordStep1Fragment(), getActivity().getSupportFragmentManager(), R.id.user_cycle_lu, null, null);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rememberUser();
        bind.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        rememberUser();
    }

    public void saveData(String apiToken, String phone, String password) {
        clean(getActivity());
        SaveData(getActivity(), API_TOKEN, apiToken);
        /*SaveData(getActivity(), NAME, name);
        SaveData(getActivity(), EMAIL, email);
        SaveData(getActivity(), PHONE, phone);
        SaveData(getActivity(), BIRTH_DATE, birthday);
        SaveData(getActivity(), LAST_DONATION, donatioLastDate);
        SaveData(getActivity(), CITY, cityId);
        //SaveData(getActivity(), PASSWORD, password);
        */

        SaveData(getActivity(), PHONE, phone);
        SaveData(getActivity(), PASSWORD, password);

    }
}
