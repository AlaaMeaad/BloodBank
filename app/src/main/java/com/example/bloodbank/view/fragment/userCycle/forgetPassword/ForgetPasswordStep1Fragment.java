package com.example.bloodbank.view.fragment.userCycle.forgetPassword;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiServers;
import com.example.bloodbank.data.model.resetPassword.ResetPassword;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.userCycle.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;
import static com.example.bloodbank.helper.HelperMethod.dismissProgressDialog;
import static com.example.bloodbank.helper.HelperMethod.showProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordStep1Fragment extends BaseFragment {

    @BindView(R.id.phone_forget_password)
    EditText phoneForgetPassword;
    ApiServers apiServers;

    public ForgetPasswordStep1Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_forget_password_step1, container, false);
        ButterKnife.bind(this, view);
        //initFragment();
        apiServers = getClient().create(ApiServers.class);
        return view;
    }

    public String allFilds() {
        String phone = phoneForgetPassword.getText().toString();

        resetPassword(phone);

        return phone;
    }

    private void resetPassword(String phone) {
        showProgressDialog(getActivity(), "Loading");
        apiServers.resetPassword(phone).enqueue(new Callback<ResetPassword>() {

            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                dismissProgressDialog();
                try {
                    if (response.body().getStatus() == 1) {
                        Integer pinCodeForTest = response.body().getData().getPinCodeForTest();
                        response.body().getData().setPinCodeForTest(pinCodeForTest);

                        ForgetPasswordStep2Fragment passwordStep2Fragment =
                                new ForgetPasswordStep2Fragment();
                        Bundle b = new Bundle();
                        b.putString("phone", phone);
                        passwordStep2Fragment.setArguments(b);
                        ReplaceFragment(passwordStep2Fragment, getActivity().getSupportFragmentManager(), R.id.user_cycle_lu, null, null);
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBack() {
        ReplaceFragment(new LoginFragment(), getFragmentManager(), R.id.user_cycle_lu, null, null);

    }

    @OnClick(R.id.send_forget_password)
    public void onViewClicked() {
        allFilds();
    }
}
