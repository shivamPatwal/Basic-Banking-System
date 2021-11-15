package com.example.bankingapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;
import com.example.bankingapp.activities.userDetailActivity;
import com.example.bankingapp.databaseHelper.MyDatabaseHelper;
import com.example.bankingapp.modals.usersList;

import java.util.ArrayList;

public class userListAdapter extends RecyclerView.Adapter<userListAdapter.userViewHolder> {
    private ArrayList<usersList> userArr;
    private  Context context;
    usersList users;
   public  static String name,phoneno,ifsccode,email,acountno,acountbal;
public  static int id;
    public userListAdapter(){}
    public userListAdapter(Context context, ArrayList<usersList> arrlist){
        this.context=context;
        userArr=arrlist;
    }

    @NonNull
    @Override
    public userListAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.userlist,parent,false);

        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userListAdapter.userViewHolder holder, int position) {
  users=userArr.get(position);
  holder.username.setText(users.getName());
  holder.accountBalance.setText(users.getAccountBalance());
   holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
       @Override
       public boolean onLongClick(View view) {
          confirmDialog(position);

           return false;
       }
   });


    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name=userArr.get(position).getName();
            Log.d("er",users.getName());
            phoneno=userArr.get(position).getPhoneNo();
            ifsccode=userArr.get(position).getIfscCode();
            email=userArr.get(position).getEmailId();
            acountbal=userArr.get(position).getAccountBalance();
            acountno=userArr.get(position).getAccountNo();
            id=userArr.get(position).getId();
            Intent i=new Intent(context, userDetailActivity.class);
           context.startActivity(i);




        }
    });


    }

    @Override
    public int getItemCount() {
        return userArr.size();
    }

    public class userViewHolder extends  RecyclerView.ViewHolder {
TextView username,accountBalance;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);

           username=itemView.findViewById(R.id.username);
            accountBalance=itemView.findViewById(R.id.amount);

        }
    }


    private void confirmDialog(int position) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete ?");
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                myDB.deleteOneRow(String.valueOf(users.getId()));

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }



    public  String getName() {
        return name;
    }

    public  String getPhoneno() {
        return phoneno;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public  String getEmail() {
        return email;
    }

    public  String getAcountno() {
        return acountno;
    }

    public  String getAcountbal() {
        return acountbal;
    }

    public  String getId() {
        return String.valueOf(id);
    }


}



