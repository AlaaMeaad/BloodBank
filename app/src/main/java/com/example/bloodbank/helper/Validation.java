package com.example.bloodbank.helper;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yehia on 26/05/2019.
 */

public class Validation {

//    public static boolean setEmailValidation(Activity activity, TextInputLayout textInputLayout, String email, int color) {
//
//        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        if (!email.matches(emailPattern)) {
//            customToast(activity, activity.getString(R.string.invalid_email));
//
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(R.color.error));
//            textInputLayout.requestFocus();
//            return false;
//        } else {
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(color));
//            return true;
//        }
//    }

    // this method validate Text Length Validation
//    public static boolean setTextLengthValidation(Activity activity, TextInputLayout textInputLayout, int length, String message, int color) {
//
//        String text = textInputLayout.getEditText().getText().toString();
//
//        if (text.length() < length) {
//            customToast(activity, message);
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(R.color.error));
//            textInputLayout.requestFocus();
//            return false;
//        } else {
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(color));
//            return true;
//        }
//    }
//
////    // this method validate confirmation  password you have to put password editText field and confirm password edit text field
////    public static boolean setConfirmPassword(Activity activity, TextInputLayout textInputLayout, String password, String confirmPassword, int color) {
////
////        if (!password.equals(confirmPassword)) {
////            customToast(activity, activity.getString(R.string.invalid_confirm_password));
////            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(R.color.error));
////            textInputLayout.requestFocus();
////            return false;
////        } else {
////            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(color));
////            return true;
////        }
////
////
////    }
//
//    //  this method to validate any editText for not null
//    public static boolean setEmptyValidation(Activity activity, TextInputLayout textInputLayout, String text, String message, int color) {
//
//        if (TextUtils.isEmpty(text)) {
//            customToast(activity, message);
//
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(R.color.error));
//            textInputLayout.requestFocus();
//            return false;
//        } else {
//            textInputLayout.setDefaultHintTextColor(activity.getResources().getColorStateList(color));
//            return true;
//        }
//    }
//
//    //  this method to validate any editText for not null
//    public static boolean setEmptyValidation(Activity activity, List<TextInputLayout> textInputLayoutList, int color) {
//
//        List<Boolean> allNotEmpty = new ArrayList<>();
//
//        for (int i = 0; i < textInputLayoutList.size(); i++) {
//            if (TextUtils.isEmpty(textInputLayoutList.get(i).getEditText().getText().toString())) {
//                textInputLayoutList.get(i).setDefaultHintTextColor(activity.getResources().getColorStateList(R.color.error));
//
//                allNotEmpty.add(false);
//            } else {
//                textInputLayoutList.get(i).setDefaultHintTextColor(activity.getResources().getColorStateList(color));
//                allNotEmpty.add(true);
//            }
//        }
//
//        if (!allNotEmpty.contains(true)) {
//            customToast(activity, activity.getString(R.string.invalid_all_data));
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//
//    public static void customToast(Activity activity, String ToastTitle) {
//
//        LayoutInflater inflater = activity.getLayoutInflater();
//
//        View layout = inflater.inflate(R.layout.toast,
//                (ViewGroup) activity.findViewById(R.id.toast_layout_root));
//
//        TextView text = layout.findViewById(R.id.text);
//        text.setText(ToastTitle);
//
//        Toast toast = new Toast(activity);
//        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 100);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
//    }
}
