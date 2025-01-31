package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Admin_Dashboard extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 100;
    ImageView imageV;
    @SuppressLint("MissingInflatedId")
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
    public void opengallery(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            try {
                android.net.Uri getimageuri=data.getData();
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),getimageuri);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
                imageV.setImageBitmap(resizedBitmap);
                String Base64String=convertBitmapToBase64(bitmap);


            }
            catch (ActivityNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAddItemDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.additem, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        TextView name=dialogView.findViewById(R.id.itemname);
        TextView price = dialogView.findViewById(R.id.price);
        Spinner category = dialogView.findViewById(R.id.Category);
        TextView stock = dialogView.findViewById(R.id.Stock);
        Button upload = dialogView.findViewById(R.id.picbtn);
        ImageView imageV = dialogView.findViewById(R.id.imageview);
        upload.setOnClickListener(v->opengallery());




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
    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  // Create a byte array output stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);  // Compress the Bitmap as PNG with quality 100%
        byte[] byteArray = byteArrayOutputStream.toByteArray();  // Get the byte array from the output stream
        return Base64.encodeToString(byteArray, Base64.DEFAULT);  // Encode the byte array to Base64 and return as a string
    }
}
