package com.project.diary.MyDiary.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.diary.MyDiary.EditNotes;
import com.project.diary.MyDiary.R;
import com.project.diary.MyDiary.models.GetDataModel;

import java.util.ArrayList;

public class GetDataAdapter extends RecyclerView.Adapter<GetDataAdapter.ViewHolder> {

    private ArrayList<GetDataModel> myarrayList;
    private FragmentActivity context;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private EditNotes editNotes;

    public GetDataAdapter(ArrayList<GetDataModel> myarrayList, FragmentActivity context) {
        this.myarrayList = myarrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GetDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phone_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetDataAdapter.ViewHolder viewHolder, int i) {
        final GetDataModel getDataModel = myarrayList.get(i);
        viewHolder.fname.setText(getDataModel.getFirstname());
        viewHolder.lname.setText(getDataModel.getLastname());
        viewHolder.phone.setText(getDataModel.getPhone());
       viewHolder.btn_relate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+getDataModel.getId(), Toast.LENGTH_SHORT).show();
                EditNotes.id = getDataModel.getId();
                EditNotes.fname = getDataModel.getFirstname();
                EditNotes.lname = getDataModel.getLastname();
                EditNotes.phone = getDataModel.getPhone();
                fragmentManager = context.getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                editNotes = new EditNotes();
                fragmentTransaction.replace(R.id.replacement_layout, editNotes,"account").addToBackStack("account").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myarrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,fname,lname,phone;
        LinearLayout btn_relate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_id);
            fname = itemView.findViewById(R.id.text_fname);
            lname = itemView.findViewById(R.id.text_lname);
            phone = itemView.findViewById(R.id.text_phone);
            btn_relate = itemView.findViewById(R.id.btn_relative);
        }
    }
}
