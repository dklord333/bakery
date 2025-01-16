package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
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

        binding.item.setOnClickListener(v -> showaddItem());


    }
    public void showaddItem(){
     LayoutInflater inflater=getLayoutInflater();
     View dialogview=inflater.inflate(R.layout.additem,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogview);


    }
}