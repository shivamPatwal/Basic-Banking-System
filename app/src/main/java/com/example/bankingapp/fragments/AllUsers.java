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
import com.example.bankingapp.activities.MainActivity;
import com.example.bankingapp.databaseHelper.MyDatabaseHelper;
import com.example.bankingapp.modals.usersList;

import com.example.bankingapp.adapters.userListAdapter;


import java.util.ArrayList;

import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_ACCOUNT_BALANCE;
import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_ACCOUNT_NUMBER;
import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_EMAIL;
import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_IFSC_CODE;
import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_NAME;
import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_USER_PHONE_NO;

import static com.example.bankingapp.databaseHelper.MyDatabaseHelper.COLUMN_ID;

public class AllUsers extends Fragment {
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<usersList> userlist;
    public AllUsers() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.userrecyclerview, container, false);

        empty_imageview = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);
        recyclerView = view.findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(getContext());
userlist=new ArrayList<>();

        storeDataInArrays();

        userListAdapter alluser = new userListAdapter(getContext(),userlist);
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
            int phoneNoColumnIndex = cursor.getColumnIndex(COLUMN_USER_PHONE_NO);
            int emailColumnIndex = cursor.getColumnIndex(COLUMN_USER_EMAIL);
            int ifscCodeColumnIndex = cursor.getColumnIndex(COLUMN_USER_IFSC_CODE);
            int accountNumberColumnIndex = cursor.getColumnIndex(COLUMN_USER_ACCOUNT_NUMBER);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_USER_NAME);
            int accountBalanceColumnIndex = cursor.getColumnIndex(COLUMN_USER_ACCOUNT_BALANCE);

            while (cursor.moveToNext()){
                String currentName = cursor.getString(nameColumnIndex);
                int accountNumber = cursor.getInt(accountNumberColumnIndex);
                String email = cursor.getString(emailColumnIndex);
                String phoneNumber = cursor.getString(phoneNoColumnIndex);
                String ifscCode = cursor.getString(ifscCodeColumnIndex);
               int Id=cursor.getInt(id);
                double accountBalance = cursor.getInt(accountBalanceColumnIndex);

                userlist.add(new usersList(Id,currentName, accountNumber,  email, ifscCode ,phoneNumber, accountBalance));
            }




            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }}


}