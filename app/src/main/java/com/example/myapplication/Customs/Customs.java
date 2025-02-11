package com.example.myapplication.Customs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


import com.example.myapplication.R;
import com.example.myapplication.Support_clases.Authentication;

public class Customs {
    private  Context context;
    private static AlertDialog alertDialog;

    public Customs(Context context) {
        this.context = context;
    }

    public  void showAlert( String title, String message){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);



        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View Dialogview= layoutInflater.inflate(R.layout.alertbox,null);
        TextView Title=Dialogview.findViewById(R.id.dialogTitle);
        TextView Message=Dialogview.findViewById(R.id.dialogMessage);
        TextView cancelbtn=Dialogview.findViewById(R.id.closeButton);

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
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setGravity(Gravity.TOP);

        alertDialog.show();
        Animation animation=AnimationUtils.loadAnimation(context,R.anim.slide_in);
        Dialogview.startAnimation(animation);

        if (alertDialog.getWindow() != null){
            Activity activity= (Activity) context;
            DisplayMetrics displayMetrics=new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            int width=(int) (displayMetrics.widthPixels*0.85);
            int height=(int) (displayMetrics.heightPixels*0.25);
            alertDialog.getWindow().setLayout(width,height);
        };






    }
    public  void showorderpage(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.additem, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);



    }
}
