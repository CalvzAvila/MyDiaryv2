package com.project.diary.MyDiary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.diary.MyDiary.models.DeleteModel;
import com.project.diary.MyDiary.models.UpdateModel;
import com.project.diary.MyDiary.service.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditNotes extends android.support.v4.app.Fragment{

    View view;
    public static int id;
    public  static  String title,contents;
    //TextView contact_id;
    EditText etTitle,etContents;
    FloatingActionButton btn_Update,btn_delete;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction,fragmentTransaction1;
    AccountFragment accountFragment;

    private static final String TAG = "EditNotes";

    @SuppressLint("SetTextI18n")
    private void MyViews(View view){

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction1 = fragmentManager.beginTransaction();
        accountFragment = new AccountFragment();

       // contact_id = view.findViewById(R.id.text_contact_id);
        etTitle = view.findViewById(R.id.editTitle);
        etContents = view.findViewById(R.id.editContents);
        btn_Update = view.findViewById(R.id.btnUpdate);
        btn_delete = view.findViewById(R.id.btnDelete);

        //contact_id.setText(""+id);
        etTitle.setText(title);
        etContents.setText(contents);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Updater();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deleter();
            }
        });


    }

    private void Updater(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        Call<UpdateModel> updateModelCall = serviceApi.isUpdate(
                id,etTitle.getText().toString(),etContents.getText().toString());

        updateModelCall.enqueue(new Callback<UpdateModel>() {
            @Override
            public void onResponse(Call<UpdateModel> call, Response<UpdateModel> response) {
                if (response.body().getSuccess() == 1){
                    Toast.makeText(getActivity(), "Update success!", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.replacement_layout,accountFragment,null).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Update failed!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UpdateModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void Deleter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        Call<DeleteModel> deleteModelCall = serviceApi.isDelete(id);
        deleteModelCall.enqueue(new Callback<DeleteModel>() {
            @Override
            public void onResponse(Call<DeleteModel> call, Response<DeleteModel> response) {
                if (response.body().getSuccess() == 1){
                    Toast.makeText(getActivity(), "Delete success!", Toast.LENGTH_SHORT).show();
                    fragmentTransaction1.replace(R.id.replacement_layout,accountFragment,null).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Delete failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_edit_notes, container, false);
        MyViews(view);
        return view;
    }

}
