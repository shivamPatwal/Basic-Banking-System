package com.example.bankingapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;
import com.example.bankingapp.adapters.TranscationAdapter;
import com.example.bankingapp.adapters.userListAdapter;
import com.example.bankingapp.databaseHelper.MyDatabaseHelper;
import com.example.bankingapp.databaseHelper.TranscationDbHelper;
import com.example.bankingapp.modals.transcations;
import com.example.bankingapp.modals.usersList;

import java.util.ArrayList;

import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_ID;
import static com.example.bankingapp.databaseHelper.TranscationDbHelper.COLUMN_DATE;
import static com.example.bankingapp.databaseHelper.TranscationDbHelper.COLUMN_SENDER_NAME;
import static com.example.bankingapp.databaseHelper.TranscationDbHelper.COLUMN_RECEIVER_NAME;
import static com.example.bankingapp.databaseHelper.TranscationDbHelper.COLUMN_AMOUNT_SENT;

public class AllTranscations extends Fragment {

    RecyclerView recyclerView;

    TranscationDbHelper myDB;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<transcations> userlist;

    public AllTranscations() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.userrecyclerview, container, false);

        empty_imageview = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);
        recyclerView = view.findViewById(R.id.recyclerView);
        myDB = new TranscationDbHelper(getContext());
        userlist=new ArrayList<>();

        storeDataInArrays();

        TranscationAdapter alluser = new TranscationAdapter(getContext(),userlist);
        recyclerView.setAdapter(alluser);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            int id=cursor.getColumnIndex(COLUMN_ID);
            int sendernameIndex = cursor.getColumnIndex(COLUMN_SENDER_NAME);
            int receivernameIndex = cursor.getColumnIndex(COLUMN_RECEIVER_NAME);
            int accountIndex = cursor.getColumnIndex(COLUMN_AMOUNT_SENT);
            int dateIndex=cursor.getColumnIndex(COLUMN_DATE);
            while (cursor.moveToNext()){
                String senderName = cursor.getString(sendernameIndex);
                String ReciverName = cursor.getString(receivernameIndex);
                double amount  = cursor.getDouble(accountIndex);
                String date=cursor.getString(dateIndex);
                int Id=cursor.getInt(id);
                userlist.add(new transcations(Id,senderName,ReciverName,amount,date));
            }




            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }}


}