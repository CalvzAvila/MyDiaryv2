package com.project.diary.MyDiary;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.replacement_layout, loginFragment,"signin").commit();
    }
}
