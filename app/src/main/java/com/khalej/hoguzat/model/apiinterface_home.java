package com.khalej.hoguzat.model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface apiinterface_home {

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_login.php")
    Call<List<contact_userinfo>> getcontacts_login(@Field("phonee") String phone, @Field("passw") String password);
    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_Registraion.php")
    Call<ResponseBody> getcontacts_newaccount(@Field("name") String name, @Field("password") String password, @Field("address") String address,
                                              @Field("phone") String phone);

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_menSalon_category.php")
    Call<List<contact_category>>getSalonCategry(@Field("id") int id,@Field("country") int country ,@Field("gender") int gender);

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_search.php")
    Call<List<contact_category>>getSalonCategrySearch(@Field("id") int id,@Field("country") int country ,@Field("gender") int gender,
                                                      @Field("city") String city);

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_menSalon_category_stuff.php")
    Call<List<content_stuff>>getStuff(@Field("id") int id);

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_menSalon_category_servcies.php")
    Call<List<content_servcies>>getServcies(@Field("id") int id);

    @FormUrlEncoded
    @POST("montag/Hoguzat/Hoguzat_add_order.php")
    Call<ResponseBody> getcontacts_order(@Field("name") String name, @Field("address") String address,
                                         @Field("phone") String phone,
                                         @Field("details") String details,
                                         @Field("price")double price

    );
}

