package com.example.myapplication.Customs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.zip.Inflater;

public class Customs {
    private Context context;
    private AlertDialog alertDialog;

    public Customs(Context context) {
        this.context = context;
    }

    public static void showAlert(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View Dialogview= layoutInflater.inflate(R.layout.alertbox,null);
        TextView Title=Dialogview.findViewById(R.id.dialogTitle);
        TextView Message=Dialogview.findViewById(R.id.dialogMessage);
        Button cancelbtn=Dialogview.findViewById(R.id.closeButton);

         Title.setText(title);
         Message.setText(message);
         cancelbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 alertDialog.dismiss();
             }
         });
         builder.setView(Dialogview);
         alertDialog=builder.create();
         alertDialog.show();




    }
}
