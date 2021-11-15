package com.example.bankingapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;
import com.example.bankingapp.activities.MainActivity;
import com.example.bankingapp.databaseHelper.TranscationDbHelper;
import com.example.bankingapp.modals.transcations;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TranscationAdapter extends RecyclerView.Adapter<TranscationAdapter.userViewHolder> {
    private ArrayList<transcations> userArr;
    private Context context;
    transcations users;

    public TranscationAdapter(Context context, ArrayList<transcations> arrlist) {
        this.context = context;
        userArr = arrlist;
    }

    @NonNull
    @Override
    public TranscationAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.trancationlist, parent, false);

        return new TranscationAdapter.userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranscationAdapter.userViewHolder holder, int position) {

        users = userArr.get(position);
        holder.sendername.setText(users.getSender_name());
        holder.revceivername.setText(users.getReceiver_name());

        holder.amountsend.setText("rs: "+ users.getMoney());

        if (users.getReceiver_name().equals("Transcation Failed")) {
            holder.itemView.setBackgroundColor(Color.parseColor("#fc7c72"));
            holder.i1.setImageResource(R.drawable.error);
            holder.setdate.setText("Transcation Failed");
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#cafcb4"));
            holder.i1.setImageResource(R.drawable.tick);

            holder.setdate.setText(userArr.get(position).getDate());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArr.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        TextView amountsend, sendername, revceivername,setdate;
        ImageView i1;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            revceivername = itemView.findViewById(R.id.reiceivername);
            sendername = itemView.findViewById(R.id.t_from_name);
            amountsend = itemView.findViewById(R.id.t_amount);
            i1=itemView.findViewById(R.id.t_amount1);
setdate=itemView.findViewById(R.id.date);
        }
    }


    private void confirmDialog(int position) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete ?");
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TranscationDbHelper myDB = new TranscationDbHelper(context);
                myDB.deleteOneRow(String.valueOf(users.getId()));
                Intent in = new Intent(context, MainActivity.class);
                context.startActivity(in);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
}



