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
    @POST("/diaryfiles2/login.php")
    Call<LoginModel> isTrue(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("/diaryfiles2/register.php")
    Call<RegisterModel> isRegister(@Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("/diaryfiles2/save.php")
    Call<SavePhoneModel> isSaved(@Field("user_id") int user_id,
                                 @Field("firstname") String firstname,
                                 @Field("lastname") String lastname,
                                 @Field("phone") String phone);

    @FormUrlEncoded
    @POST("/diaryfiles2/getdata.php")
    Call<ArrayList<GetDataModel>> phoneData(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("/diaryfiles2/updater.php")
    Call<UpdateModel> isUpdate(@Field("id") int id,
                               @Field("firstname") String firstname,
                               @Field("lastname") String lastname,
                               @Field("phone") String phone);


    @FormUrlEncoded
    @POST("/diaryfiles2/deleter.php")
    Call<DeleteModel> isDelete(@Field("id") int id);

}
