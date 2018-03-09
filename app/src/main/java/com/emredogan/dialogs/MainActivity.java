package com.emredogan.dialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    Timer timer;

    int i = 0;

    AlertDialog alertDialog;
    AlertDialog confirmationAlertDialog;

    ProgressDialog progressDialog;


    AlertDialog.Builder builder;
    AlertDialog.Builder confirmationBuilder;

    String result = "";

    String[] items = {"Easy","Normal","Hard","Die Hard"};

    Button normalDialogButton;
    Button confirmationButton;
    Button progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normalDialogButton = (Button) findViewById(R.id.dialogButton);
        confirmationButton = (Button) findViewById(R.id.confirmationDialogButton);
        progressButton = (Button) findViewById(R.id.progressButton);

        progressDialog = new ProgressDialog(this);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setIndeterminate(false);

        progressDialog.setTitle("Progress Dialog");

        progressDialog.setMessage("Please wait...");

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                System.out.println(i);

                i = i+5;

                if (i<=100) {

                    progressDialog.setProgress(i);

                } else {

                    timer.cancel();
                    progressDialog.cancel();
                    i=0;

                }

            }
        };

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        },8000,500);






        builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);

        confirmationBuilder = new AlertDialog.Builder(this, R.style.ConfirmationTheme);

        confirmationBuilder.setTitle("Select Difficulty Level");

        confirmationBuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                result = items[i];


            }
        });

        confirmationBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();


                
            }
        });
        
        confirmationBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                
            }
        });

        confirmationAlertDialog = confirmationBuilder.create();

        builder.setMessage("Discard Draft");

        builder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Approve", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Discard", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog = builder.create();


      //  alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alertDialog));

      //  alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alertDialog));

        normalDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.show();

            }
        });

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationAlertDialog.show();
            }
        });

        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
            }
        });
    }


}
