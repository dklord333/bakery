package com.example.myapplication;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Admin_Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_dashboard);  // Replace with your layout
        RecyclerView recyclerView = findViewById(R.id.adminItem);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 items per row
        admin_item_adapter adapter = new admin_item_adapter();
        recyclerView.setAdapter(adapter);

        findViewById(R.id.item).setOnClickListener(view  -> showAddItemDialog());
    }

    private void showAddItemDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.additem, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Adjust dialog size based on screen metrics
        if (dialog.getWindow() != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (int) (displayMetrics.widthPixels * 0.83);
            int height = (int) (displayMetrics.heightPixels * 0.83);
            dialog.getWindow().setLayout(width, height);
        }

        Spinner spinner = dialogView.findViewById(R.id.Category);  // Your Spinner ID
        String[] spinnerItems = {"Select Category", "Cakes", "Biscuits", "Brownies", "Baked Items", "Donuts, Sundaes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                else{
                    return true;
                }// Disable first item
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);  // Gray color for the first item
                }



                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
