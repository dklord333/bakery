package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AdapterClases.Orderlist_adapter;
import com.example.myapplication.AdapterClases.admin_item_adapter;
import com.example.myapplication.Customs.Customs;
import com.example.myapplication.ModelClass.ItemModel;
import com.example.myapplication.ModelClass.Order;
import com.example.myapplication.Support_clases.DialogC;
import com.example.myapplication.Support_clases.Orderlist;
import com.example.myapplication.Support_clases.Validations;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin_Dashboard extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 100;
    ImageView imageV;
    String base64image;
    List<ItemModel> itemModelList;
    List<Order>ordermodellist;
    admin_item_adapter adminItemAdapter;
    Orderlist_adapter orderlist_adapter;
    String TAG = Admin_Dashboard.class.getName();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_dashboard);

        itemModelList=new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.adminItem);
        recyclerView.setLayoutManager(new GridLayoutManager(Admin_Dashboard.this, 2)); // 2 items per row
        adminItemAdapter = new admin_item_adapter(this,itemModelList);
        recyclerView.setAdapter(adminItemAdapter);
        FetctfromFirebase();


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
                base64image=convertBitmapToBase64(bitmap);


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
        TextView Description=dialogView.findViewById(R.id.Description);
        Spinner category = dialogView.findViewById(R.id.Category);
        TextView stock = dialogView.findViewById(R.id.Stock);
        Button upload = dialogView.findViewById(R.id.picbtn);
        ImageButton cancelbtn= dialogView.findViewById(R.id.cancelDialog);
        imageV = dialogView.findViewById(R.id.imageview);
        upload.setOnClickListener(v->opengallery());
        Button additem=dialogView.findViewById(R.id.Submit);
        
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName=name.getText().toString().trim();
                String itemprice=price.getText().toString().trim();
                String itemcategory=category.getSelectedItem().toString().trim();
                String itemstock=stock.getText().toString().trim();
                String itemimage=base64image;
                String itemdescription=Description.getText().toString().trim();

                Log.d("imageid", "this is : "+itemimage);
                Log.d("catehory","category is"+itemcategory);
                Log.d("Name","name is"+itemName);


                ItemModel itemModel=new ItemModel(itemName, itemprice, itemstock, itemcategory,itemdescription,itemimage);
                if(Validations.isItemEmpty(itemModel)){
                    Customs customs=new Customs(Admin_Dashboard.this);
                    customs.showAlert("failed","compelete the form");
                }

                Addtofirestore(Admin_Dashboard.this,itemModel);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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

    private  static void Addtofirestore(Context context,ItemModel itemModel) {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        //String userid=user.getUid();
long date=Customs.DateTime();
        Customs customs=new Customs(context);
        FirebaseFirestore.getInstance().collection("ItemList").document(String.valueOf(date)).set(itemModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        customs.showAlert("Success","Item Created");
                    }
                })
                .addOnFailureListener(e -> customs.showAlert("Failure","cannot Add Item "+e),e -> Log.d("failure", "Addtofirestore: Error"+e)
                );

    }

    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  // Create a byte array output stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);  // Compress the Bitmap as PNG with quality 100%
        byte[] byteArray = byteArrayOutputStream.toByteArray();  // Get the byte array from the output stream
        return Base64.encodeToString(byteArray, Base64.DEFAULT);  // Encode the byte array to Base64 and return as a string
    }
    public void FetctfromFirebase(){
        Log.d(TAG, "FetctfromFirebase: Called");
        FirebaseFirestore.getInstance().collection("ItemList").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d(TAG, "onSuccess: snapshot received");
                itemModelList.clear();
                for(DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Log.d(TAG, "onSuccess: Item = " + documentSnapshot.get(documentSnapshot.getId(), ItemModel.class));
                    Log.d(TAG, "onSuccess: Object = " + documentSnapshot.toObject(ItemModel.class));
                    ItemModel itemModel=documentSnapshot.toObject(ItemModel.class);
                    itemModelList.add(itemModel);

                }
                adminItemAdapter.notifyDataSetChanged();

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
        findViewById(R.id.orderlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordermodellist=new ArrayList<>();
                fetchdatafromOrderList();
                orderlist_adapter=new Orderlist_adapter(Admin_Dashboard.this,ordermodellist);

                Intent intent=new Intent(Admin_Dashboard.this, Orderlist.class);
                startActivity(intent);
            }
        });

    }

    private void fetchdatafromOrderList() {

        FirebaseFirestore.getInstance().collection("OrderInfo").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
                public void onSuccess(QuerySnapshot documentSnapshot) {
                if(ordermodellist!=null) {
                    ordermodellist.clear();
                }

                for(DocumentSnapshot snapshot: documentSnapshot){
                    Order ordermodel=snapshot.toObject(Order.class);
                    ordermodellist.add(ordermodel);
                }
                orderlist_adapter.notifyDataSetChanged();

            }
        });
    }
}
