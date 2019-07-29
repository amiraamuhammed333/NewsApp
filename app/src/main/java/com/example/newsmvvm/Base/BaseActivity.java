package com.example.newsmvvm.Base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsmvvm.R;

public class BaseActivity extends AppCompatActivity {

    public AppCompatActivity activity;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        activity=this;
    }
    public AlertDialog showConfirmationDialog(int title,int message,int posText
            ,AlertDialog.OnCancelListener posAction,boolean isCancelable){
         alertDialog = new AlertDialog .Builder(activity)
                .setTitle ( title )
                .setMessage ( message )
                .setPositiveButton ( posText, (DialogInterface.OnClickListener) posAction )
                 .setCancelable ( isCancelable )
                 .show ();
         return alertDialog;


    }



    public AlertDialog showMessage(int title,int message,int posText
            ){
        alertDialog = new AlertDialog .Builder(activity)
                .setTitle ( title )
                .setMessage ( message )
                .setPositiveButton ( posText, new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                } )
                .show ();
        return alertDialog;


    }



    public AlertDialog showConfirmationDialog(String title,String message,String posText
            ,AlertDialog.OnCancelListener posAction,boolean isCancelable){
        alertDialog = new AlertDialog .Builder(activity)
                .setTitle ( title )
                .setMessage ( message )
                .setPositiveButton ( posText, (DialogInterface.OnClickListener) posAction )
                .setCancelable ( isCancelable )
                .show ();
        return alertDialog;


    }



    public AlertDialog showMessage(String title,String message,String posText
    ){
        alertDialog = new AlertDialog .Builder(activity)
                .setTitle ( title )
                .setMessage ( message )
                .setPositiveButton ( posText, new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                } )
                .show ();
        return alertDialog;


    }



    ProgressDialog progressDialog;
    public ProgressDialog showProgreesBar(int message){
        progressDialog = new ProgressDialog ( activity);
        progressDialog.setTitle ( message );
        progressDialog.setCancelable ( false );
        progressDialog.show ();
        return progressDialog;


    }

    public void hideProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss ();
        }
    }








}












