package com.example.myapplication;

import android.app.AlertDialog;
 import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityAdminDashboardBinding;

public class Admin_Dashboard extends AppCompatActivity {
    ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding=ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.item.setOnClickListener(view  -> showaddItem());


    }
    public void showaddItem(){
     LayoutInflater inflater=getLayoutInflater();
     View dialogview=inflater.inflate(R.layout.additem,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogview);
           AlertDialog dialog=builder.create();
           dialog.show();
if (dialog.getWindow() !=null){
    DisplayMetrics displayMetrics=new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    int width=(int)(displayMetrics.widthPixels*0.83);
    int height=(int)(displayMetrics.heightPixels*0.83);
    dialog.getWindow().setLayout(width,height);

}
    }
}