package com.example.bankingapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;
import com.example.bankingapp.activities.MainActivity;
import com.example.bankingapp.activities.SendToUserListActivity;
import com.example.bankingapp.activities.userDetailActivity;
import com.example.bankingapp.databaseHelper.MyDatabaseHelper;
import com.example.bankingapp.databaseHelper.TranscationDbHelper;
import com.example.bankingapp.fragments.AllUsers;
import com.example.bankingapp.modals.usersList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class sendUserListAdapter extends RecyclerView.Adapter<sendUserListAdapter.userViewHolder> {
    private ArrayList<usersList> userArr;
    private Context context;
    usersList users;

    public sendUserListAdapter(Context context, ArrayList<usersList> arrlist) {
        this.context = context;
        userArr = arrlist;
    }

    @NonNull
    @Override
    public sendUserListAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.userlist, parent, false);

        return new sendUserListAdapter.userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sendUserListAdapter.userViewHolder holder, int position) {
        users = userArr.get(position);
        holder.username.setText(users.getName());
        holder.accountBalance.setText(users.getAccountBalance());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(view, position);
            }
        });


    }

    private void confirmDialog(View v, int position) {
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String date = currentTime + "," + currentDate;
        TranscationDbHelper dbHelper = new TranscationDbHelper(context);
        userDetailActivity a = new userDetailActivity();
        dbHelper.addTranscation(a.getFROM_USER_NAME(), userArr.get(position).getName(), a.getTRANSFER_AMOUNT(), date);

        MyDatabaseHelper helper = new MyDatabaseHelper(context);
        helper.updateData(String.valueOf(userArr.get(position).getId()), a.getTRANSFER_AMOUNT() + userArr.get(position).getAccountBal());
        helper.updateData(a.getId(), a.getFromUserAccountBalance() - a.getTRANSFER_AMOUNT());
       run(v);

    }

    private void run(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog, viewGroup, false);
        Button b1=dialogView.findViewById(R.id.b3);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
        alertDialog.show();
    }


    @Override
    public int getItemCount() {
        return userArr.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        TextView username, accountBalance;


        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            accountBalance = itemView.findViewById(R.id.amount);
        }
    }

}





