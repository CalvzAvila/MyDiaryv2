package com.project.diary.MyDiary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.diary.MyDiary.models.RegisterModel;
import com.project.diary.MyDiary.service.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterFragment extends android.support.v4.app.Fragment {

    View view;
    EditText edit_reg_firstname,edit_reg_lastname,edit_reg_email,edit_reg_user,edit_reg_pass,edit_reg_retype_pass;
    Button btn_register;
    private static final String TAG = "RegisterFragment";

    private void MyViews(View view){
        edit_reg_firstname = view.findViewById(R.id.edit_reg_firstname);
        edit_reg_lastname = view.findViewById(R.id.edit_reg_lastname);
        edit_reg_email = view.findViewById(R.id.edit_reg_email);
        edit_reg_user = view.findViewById(R.id.edit_reg_user);
        edit_reg_pass = view.findViewById(R.id.edit_reg_pass);
        edit_reg_retype_pass = view.findViewById(R.id.edit_reg_retype_pass);
        btn_register = view.findViewById(R.id.btn_reg_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });
    }

    private void RegisterUser(){

        String fname = edit_reg_firstname.getText().toString();
        String lname = edit_reg_lastname.getText().toString();
        String email = edit_reg_email.getText().toString();
        String user = edit_reg_user.getText().toString();
        String pass = edit_reg_pass.getText().toString();
        String retype_pass = edit_reg_retype_pass.getText().toString();

        if (retype_pass.equals(pass)){


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceApi serviceApi = retrofit.create(ServiceApi.class);
            Call<RegisterModel> registerModelCall = serviceApi.isRegister(
                fname,lname,email,user,pass
            );
            registerModelCall.enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                    Log.d(TAG, "onResponse: "+response.body().getSuccess());
                    if (response.body().getSuccess() == 1){
                        Toast.makeText(getActivity(), "Register success!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Register failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "Password did not match try again!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register, container, false);
        MyViews(view);
        return view;
    }

}
