package com.example.bankingapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bankingapp.R;
import com.example.bankingapp.adapters.userListAdapter;
import com.example.bankingapp.databaseHelper.TranscationDbHelper;

public class userDetailActivity extends AppCompatActivity {
    TextView name, email, ifsc, account, balnce, phone;
    Button transfer;
    Bundle b;
    AlertDialog dialog;

    public static String FROM_USER_NAME, TRANSFER_AMOUNT, FROM_USER_ACCOUNT_BALANCE;
    public static String FROM_USER_ACCOUNT_NO,id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email_id);
        ifsc = findViewById(R.id.ifsc_id);
        account = findViewById(R.id.account);
        balnce = findViewById(R.id.avail_balance);
        phone = findViewById(R.id.phone_no);
        transfer = findViewById(R.id.transfer_money);

        userListAdapter a = new userListAdapter();
        name.setText(a.getName());

        email.setText(a.getEmail());

        ifsc.setText(a.getIfsccode());
        account.setText(a.getAcountno());
        balnce.setText(a.getAcountbal());
        phone.setText(a.getPhoneno());
id=a.getId();
        Log.d("name", a.getName());
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    private void confirmDialog() {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(userDetailActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_alert_box, null);
        mBuilder.setTitle("TRANSFER").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.editText);


        FROM_USER_NAME = name.getText().toString();

        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TRANSFER_AMOUNT = mAmount.getText().toString();
                if(TRANSFER_AMOUNT.isEmpty()){ TRANSFER_AMOUNT="0.0";  }

                dialog.dismiss();
                Cancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether amount entered is correct or not
                Double currentBalance = Double.parseDouble(String.valueOf(balnce.getText()));

                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Double.parseDouble(mAmount.getText().toString()) > currentBalance) {
                    mAmount.setError("Your account don't have enough balance");
                } else {
                    TRANSFER_AMOUNT = mAmount.getText().toString();

                    FROM_USER_ACCOUNT_BALANCE = balnce.getText().toString();
                    FROM_USER_ACCOUNT_NO = account.getText().toString();
                    Intent intent = new Intent(userDetailActivity.this, SendToUserListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void Cancel() {


        AlertDialog.Builder builder = new AlertDialog.Builder(userDetailActivity.this);

        builder.setMessage("Do you want to cancel the transcation ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                TranscationDbHelper dbHelper=new TranscationDbHelper(userDetailActivity.this);

                dbHelper.addTranscation(FROM_USER_NAME,"Transcation Failed", Double.parseDouble(TRANSFER_AMOUNT),"Transcation Failed");
                Intent i=new Intent(userDetailActivity.this, MainActivity.class);
                startActivity(i);


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    public String getFROM_USER_NAME() {
        return FROM_USER_NAME;
    }

    public Double getTRANSFER_AMOUNT() {
        return Double.parseDouble(TRANSFER_AMOUNT);
    }

    public static Double getFromUserAccountBalance() {
        return Double.valueOf(FROM_USER_ACCOUNT_BALANCE);
    }

    public static String getFromUserAccountNo() {
        return FROM_USER_ACCOUNT_NO;
    }

    public static String getId() {
   return  id;

    }

}



