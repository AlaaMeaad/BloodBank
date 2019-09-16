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
import com.example.bloodbank.data.model.newPassword.NewPassword;
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
public class ForgetPasswordStep2Fragment extends BaseFragment {


    @BindView(R.id.code_forget_password)
    EditText codeForgetPassword;
    @BindView(R.id.new_password_forget_password)
    EditText newPasswordForgetPassword;
    @BindView(R.id.confirm_password_forget_password)
    EditText confirmPasswordForgetPassword;
    ApiServers apiServers;
    private String phone;

    public ForgetPasswordStep2Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_forget_password_step2, container, false);
        ButterKnife.bind(this, view);
        apiServers = getClient().create(ApiServers.class);
        if (getArguments() != null) {
            phone = getArguments().getString("phone");
        }

        return view;
    }

    private void allFileds() {
        String code = codeForgetPassword.getText().toString();
        String password = newPasswordForgetPassword.getText().toString();
        String confirmPassword = confirmPasswordForgetPassword.getText().toString();
        newPassword(password, confirmPassword, code, phone);
    }

    private void newPassword(String password, String confirmPassword, String code, String phone) {
        showProgressDialog(getActivity(), "Loading");
        apiServers.newPassword(password, confirmPassword, code, phone).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                dismissProgressDialog();
                try {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            ReplaceFragment(new LoginFragment(), getActivity().getSupportFragmentManager(),
                                    R.id.user_cycle_lu, null, null);
                        }

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public void onBack() {
        ReplaceFragment(new LoginFragment(), getFragmentManager(), R.id.user_cycle_lu, null, null);
    }

    @OnClick(R.id.send_new_password)
    public void onViewClicked() {
        allFileds();
    }
}
