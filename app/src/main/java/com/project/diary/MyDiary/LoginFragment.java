package com.project.diary.MyDiary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.diary.MyDiary.models.LoginModel;
import com.project.diary.MyDiary.service.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends android.support.v4.app.Fragment {

    View view;
    EditText edit_user,edit_pass;
    Button btn_login;
    TextView btn_register;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction,fragmentTransaction1;
    RegisterFragment registerFragment;
    AccountFragment accountFragment;
    private static final String TAG = "LoginFragment";

    @SuppressLint("CommitTransaction")
    private void MyViews(View view){
        edit_user = view.findViewById(R.id.edit_user);
        edit_pass = view.findViewById(R.id.edit_password);
        btn_login = view.findViewById(R.id.btn_sign_in);
        btn_register = view.findViewById(R.id.btn_register);

        edit_user.requestFocus();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction1 = fragmentManager.beginTransaction();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginValidation();
                clearText();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerFragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.replacement_layout,registerFragment,"register").addToBackStack("login").commit();
            }
        });
    }

    private void LoginValidation(){

        String username = edit_user.getText().toString();
        String password = edit_pass.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        Call<LoginModel> myModel = serviceApi.isTrue(
            username,password
        );



        myModel.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.body().getSuccess() == 1){
                    AccountFragment.user_id = response.body().getUser_id();
                    accountFragment = new AccountFragment();
                    fragmentTransaction1.replace(R.id.replacement_layout,accountFragment,"account").commit();
                }
                else{
                    Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void clearText()
    {
        edit_user.setText("");
        edit_pass.setText("");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        MyViews(view);
        return view;
    }

}
