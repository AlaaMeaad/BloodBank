package com.example.bloodbank.data.api;

import com.example.bloodbank.data.model.allposts.AllPosts;
import com.example.bloodbank.data.model.favouritePost.FavouritePost;
import com.example.bloodbank.data.model.listBloodTypes.BloodTypes;
import com.example.bloodbank.data.model.listCity.City;
import com.example.bloodbank.data.model.listGovern.Govern;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.newPassword.NewPassword;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.data.model.resetPassword.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServers {
    @POST("signup")
    @FormUrlEncoded
    Call<Register> createNewAccuont(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("birth_date") String birth_date,
                                    @Field("city_id") String city_id,
                                    @Field("phone") String phone,
                                    @Field("donation_last_date") String donation_last_date,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("blood_type_id") String blood_type_id);


    @GET("governorates")
    Call<Govern> getGaverment();

    @GET("cities")
    Call<City> getCity(@Query("governorate_id") int governorate_id);

    @GET("blood-types")
    Call<BloodTypes> getBloodTypes();

    @POST("login")
    @FormUrlEncoded
    Call<Login> loginBloodBank(@Field("phone") String phone,
                               @Field("password") String password);

    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> resetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("password") String password,
                                  @Field("password_confirmation") String password_confirmation,
                                  @Field("pin_code") String pin_code,
                                  @Field("phone") String phone);

    @GET("posts")
    Call<AllPosts> getAllPosts(@Query("api_token") String api_token,
                               @Query("page") int page);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<FavouritePost> favouritepost(@Field("post_id") String post_id,
                                      @Field("api_token") String api_token);
}


