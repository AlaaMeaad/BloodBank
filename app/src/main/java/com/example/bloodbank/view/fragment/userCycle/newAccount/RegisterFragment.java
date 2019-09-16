package com.example.bloodbank.view.fragment.userCycle.newAccount;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiServers;
import com.example.bloodbank.data.model.DateModel;
import com.example.bloodbank.data.model.listBloodTypes.BloodTypes;
import com.example.bloodbank.data.model.listCity.City;
import com.example.bloodbank.data.model.listGovern.Govern;
import com.example.bloodbank.data.model.register.DataRegsiter;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.userCycle.LoginFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.BloodConstants.API_TOKEN;
import static com.example.bloodbank.helper.BloodConstants.BIRTH_DATE;
import static com.example.bloodbank.helper.BloodConstants.BLOOD_TYPE;
import static com.example.bloodbank.helper.BloodConstants.CITY;
import static com.example.bloodbank.helper.BloodConstants.EMAIL;
import static com.example.bloodbank.helper.BloodConstants.LAST_DONATION;
import static com.example.bloodbank.helper.BloodConstants.NAME;
import static com.example.bloodbank.helper.BloodConstants.PHONE;
import static com.example.bloodbank.helper.HelperMethod.ReplaceFragment;
import static com.example.bloodbank.helper.HelperMethod.dismissProgressDialog;
import static com.example.bloodbank.helper.HelperMethod.showCalender;
import static com.example.bloodbank.helper.HelperMethod.showProgressDialog;
import static com.example.bloodbank.helper.ShrdPreferences.SaveData;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {

    @BindView(R.id.user_name_register)
    EditText userNameRegister;
    @BindView(R.id.email_register)
    EditText emailRegister;
    @BindView(R.id.birthday_register)
    TextView birthdayRegister;
    @BindView(R.id.type_blood_spinner_register)
    Spinner typeBloodSpinnerRegister;
    @BindView(R.id.data_tbra_register)
    TextView dataTbraRegister;
    @BindView(R.id.mohfzat_spinner_register)
    Spinner mohfzatSpinnerRegister;
    @BindView(R.id.city_spinner_register)
    Spinner citySpinnerRegister;
    @BindView(R.id.phone_register)
    EditText phoneRegister;
    @BindView(R.id.password_register)
    EditText passwordRegister;
    @BindView(R.id.confirm_password_register)
    EditText confirmPasswordRegister;

    ApiServers apiServers;
    private ArrayList<Integer> ctiyIds;
    private String startCityId;
    private String blood_TypeId;

    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        apiServers = getClient().create(ApiServers.class);
        getGaverment();
        getBloodTypes();
        //getCtiy(id);
        return view;
    }


    private void getGaverment() {
        //showProgressDialog(getActivity(),"Loading");
        apiServers.getGaverment().enqueue(new Callback<Govern>() {
            @Override
            public void onResponse(Call<Govern> call, Response<Govern> response) {
                //      dismissProgressDialog();
                List<String> names = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                names.add("اختر المحافظة");
                ids.add(0);
                for (int i = 0; i < response.body().getData().size(); i++) {
                    names.add(response.body().getData().get(i).getName());
                    ids.add(response.body().getData().get(i).getId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, names);
                mohfzatSpinnerRegister.setAdapter(adapter);
                mohfzatSpinnerRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        getCtiy(ids.get(i));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onFailure(Call<Govern> call, Throwable t) {

            }
        });
    }

    private void getBloodTypes() {
        showProgressDialog(getActivity(), "Loading");
        apiServers.getBloodTypes().enqueue(new Callback<BloodTypes>() {
            @Override
            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                dismissProgressDialog();
                List<String> names = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                names.add("اختر فصيلة الدم");
                ids.add(0);
                for (int i = 0; i < response.body().getData().size(); i++) {
                    names.add(response.body().getData().get(i).getName());
                    ids.add(response.body().getData().get(i).getId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, names);
                typeBloodSpinnerRegister.setAdapter(adapter);
                typeBloodSpinnerRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        blood_TypeId = String.valueOf(ids.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onFailure(Call<BloodTypes> call, Throwable t) {

            }
        });
    }


    private void getCtiy(Integer id) {
        //showProgressDialog(getActivity(),"Loading");
        apiServers.getCity(id).enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                //      dismissProgressDialog();
                List<String> names = new ArrayList<>();
                ctiyIds = new ArrayList<>();
                names.add("اختر المدينة");
                ctiyIds.add(0);
                for (int i = 0; i < response.body().getData().size(); i++) {
                    names.add(response.body().getData().get(i).getName());
                    ctiyIds.add(response.body().getData().get(i).getId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, names) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View dropDownView = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) dropDownView;
                        if (position == 0) {
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return dropDownView;
                    }
                };
                citySpinnerRegister.setAdapter(adapter);
                citySpinnerRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        startCityId = String.valueOf(ctiyIds.get(i));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                //Toast.makeText(baseActivity , names.get(citySpinnerRegister.getSelectedItemPosition()) + ctiyIds.get(citySpinnerRegister.getSelectedItemPosition()) , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        ReplaceFragment(new LoginFragment() ,getFragmentManager() , R.id.user_cycle_lu , null,null);
    }

    @OnClick(R.id.sign_up_register)
    public void onViewClicked() {
        showProgressDialog(getActivity(), "Loading");
        getAllFileds();

    }

    public void getAllFileds() {
        String name = userNameRegister.getText().toString();
        String email = emailRegister.getText().toString();
        String phone = phoneRegister.getText().toString();
        String birthday = birthdayRegister.getText().toString();
        String dataTbra = dataTbraRegister.getText().toString();
        String password = passwordRegister.getText().toString();
        String confirmPassword = confirmPasswordRegister.getText().toString();
        createNewAccuont(name, email, phone, birthday, dataTbra, password, confirmPassword);
    }

    private void createNewAccuont(String name, String email, String phone, String birthday, String dataTbra, String password, String confirmPassword) {

        apiServers.
                createNewAccuont(name, email, birthday, startCityId, phone, dataTbra, password, confirmPassword, blood_TypeId)
                .enqueue(new Callback<Register>() {

                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        dismissProgressDialog();
                        try {
                            if (response.body().getStatus() == 1) {
                                DataRegsiter dataRegsiter = response.body().getDataRegsiter();

                                String apiToken = dataRegsiter.getApiToken();
                                String name = dataRegsiter.getClient().getName();
                                String email = dataRegsiter.getClient().getEmail();
                                String phone = dataRegsiter.getClient().getPhone();
                                String birthday = dataRegsiter.getClient().getBirthDate();
                                String donatioLastDate = dataRegsiter.getClient().getDonationLastDate();
                                //String password = dataRegsiter.getClient()
                                String cityId = String.valueOf(dataRegsiter.getClient().getCityId());
                                String booldType = String.valueOf(dataRegsiter.getClient().getBloodTypeId());

                                SaveData(getActivity(), API_TOKEN, apiToken);
                                SaveData(getActivity(), NAME, name);
                                SaveData(getActivity(), EMAIL, email);
                                SaveData(getActivity(), PHONE, phone);
                                SaveData(getActivity(), BIRTH_DATE, birthday);
                                SaveData(getActivity(), LAST_DONATION, donatioLastDate);
                                SaveData(getActivity(), CITY, cityId);
                                //SaveData(getActivity(), PASSWORD, password);
                                SaveData(getActivity(), BLOOD_TYPE, booldType);

                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                ReplaceFragment(new LoginFragment(), getActivity().getSupportFragmentManager(), R.id.user_cycle_lu, null, null);

                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity() , e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Toast.makeText(getActivity(),  t.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });
    }

    @OnClick({R.id.birthday_register, R.id.data_tbra_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.birthday_register:
                getCalendar(birthdayRegister);
                break;
            case R.id.data_tbra_register:
                getCalendar(dataTbraRegister);

                break;
        }
    }

    private void getCalendar(TextView tex) {
        Calendar calendar = Calendar.getInstance();
        String y =String.valueOf(calendar.get(Calendar.YEAR));
        String m =String.valueOf(calendar.get(Calendar.MONTH));
        String d =String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        DateModel dateModel = new DateModel(d ,m , y ,y+"-" + m +"-" +d );
        showCalender(getActivity() ,null , tex , dateModel );


    }
}
