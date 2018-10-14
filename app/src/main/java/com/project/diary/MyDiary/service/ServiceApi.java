package com.project.diary.MyDiary.service;

import com.project.diary.MyDiary.models.DeleteModel;
import com.project.diary.MyDiary.models.GetDataModel;
import com.project.diary.MyDiary.models.LoginModel;
import com.project.diary.MyDiary.models.RegisterModel;
import com.project.diary.MyDiary.models.SavePhoneModel;
import com.project.diary.MyDiary.models.UpdateModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("/phonebook/login.php")
    Call<LoginModel> isTrue(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("/phonebook/register.php")
    Call<RegisterModel> isRegister(@Field("firstname") String firstname,
                                   @Field("lastname") String lastname,
                                   @Field("email") String email,
                                   @Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("/phonebook/save.php")
    Call<SavePhoneModel> isSaved(@Field("user_id") int user_id,
                                 @Field("firstname") String firstname,
                                 @Field("lastname") String lastname,
                                 @Field("phone") String phone);

    @FormUrlEncoded
    @POST("/phonebook/getdata.php")
    Call<ArrayList<GetDataModel>> phoneData(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("/phonebook/updater.php")
    Call<UpdateModel> isUpdate(@Field("id") int id,
                               @Field("firstname") String firstname,
                               @Field("lastname") String lastname,
                               @Field("phone") String phone);


    @FormUrlEncoded
    @POST("/phonebook/deleter.php")
    Call<DeleteModel> isDelete(@Field("id") int id);

}
