package com.project.diary.MyDiary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.diary.MyDiary.adapters.GetDataAdapter;
import com.project.diary.MyDiary.models.GetDataModel;
import com.project.diary.MyDiary.models.SaveNotesModel;
import com.project.diary.MyDiary.service.ServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountFragment extends android.support.v4.app.Fragment {

    View view;
    ImageView btn_add,btn_logout;
    static int user_id;
    String etTitle, etContents, cp;
    private RecyclerView recyclerView;
    private GetDataAdapter adapter;
    private ArrayList<GetDataModel> myList;
    private RecyclerView.LayoutManager layoutManager;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LoginFragment loginFragment;
    private static final String TAG = "AccountFragment";

    private void ShowData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        Call<ArrayList<GetDataModel>> myData = serviceApi.phoneData(user_id);
        myData.enqueue(new Callback<ArrayList<GetDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetDataModel>> call, Response<ArrayList<GetDataModel>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                myList = response.body();
                adapter = new GetDataAdapter(myList, getActivity());
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<GetDataModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void MyViews(View view) {
        btn_add = view.findViewById(R.id.btn_add);
        btn_logout = view.findViewById(R.id.btn_logout);
        recyclerView = view.findViewById(R.id.my_recycler);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();

        ShowData();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.replacement_layout, loginFragment,null).commit();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder messagebox = new AlertDialog.Builder(getActivity());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(50, 15, 50, 15);

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                final TextView header = new TextView(getActivity());
                header.setText("My Day");
                header.setTextSize(35);
                header.setTypeface(Typeface.create("Courier new", Typeface.BOLD));
                linearLayout.addView(header, params);

                final EditText title = new EditText(getActivity());
                title.setHint("title");
                title.setTypeface(Typeface.create("Courier new", Typeface.NORMAL));
                linearLayout.addView(title, params);

                final EditText contents = new EditText(getActivity());
                contents.setHint("contents");
                contents.setTypeface(Typeface.create("Courier new", Typeface.NORMAL));
                linearLayout.addView(contents, params);



                messagebox.setView(linearLayout);
                messagebox.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        etTitle = title.getText().toString();
                        etContents = contents.getText().toString();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Constants.BASEURL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
                        Call<SaveNotesModel> savePhoneModelCall = serviceApi.isSaved(
                                user_id, etTitle, etContents);

                        savePhoneModelCall.enqueue(new Callback<SaveNotesModel>() {
                            @Override
                            public void onResponse(Call<SaveNotesModel> call, Response<SaveNotesModel> response) {
                                if (response.body().getSuccess() == 1) {
                                    Toast.makeText(getActivity(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                                    //myList.clear();
                                    ShowData();
                                } else {
                                    Toast.makeText(getActivity(), "Data saved failed!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SaveNotesModel> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t.getMessage());
                            }
                        });
                    }
                }).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        MyViews(view);
        return view;
    }

}
