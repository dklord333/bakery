package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AdapterClases.cus_item_adapter;
import com.example.myapplication.ModelClass.ItemModel;
import com.example.myapplication.databinding.ActivityCustomerDashboardBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class customer_dashboard extends AppCompatActivity {
 private List<ItemModel> itemlist;
 private cus_item_adapter cusItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityCustomerDashboardBinding binding = ActivityCustomerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Spinner spinner = binding.dropdown;
        String[] spinnerItems = {"Select Category", "Cakes", "Biscuits", "Brownies", "Baked Items", "Dougnuts, Sundas"};
   ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerItems){
     @Override
     public boolean isEnabled(int position) {
         if(position==0){
             return false;
         }
         else{
             return true;
         }
     }


     public View getDropDownView(int position,View convertView,ViewGroup parent) {
         View view=super.getDropDownView(position,convertView,parent);

          view.setOnHoverListener(new View.OnHoverListener() {
              @Override
              public boolean onHover(View v, MotionEvent event) {
                  if(event.getAction()==MotionEvent.ACTION_HOVER_ENTER){
                      TooltipCompat.setTooltipText(v, "Tooltip for position " + position);
                  }
                  return false;
              }
          });
             if(position==0){
                 TextView tv=(TextView) view;
                 tv.setTextColor(Color.GRAY);
             }

return view;

     }

   };
   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   spinner.setAdapter(adapter);
        itemlist=new ArrayList<>();

       RecyclerView recyclerView=findViewById(R.id.Cus_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        cusItemAdapter=new cus_item_adapter(this,itemlist);
        recyclerView.setAdapter(cusItemAdapter);
        FetchfromFirebase();

    }
    public void FetchfromFirebase(){
        FirebaseFirestore.getInstance().collection("ItemList").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (itemlist == null) { // ✅ Prevent NullPointerException
                    itemlist = new ArrayList<>();
                }
                itemlist.clear();
               for(DocumentSnapshot document:queryDocumentSnapshots){
                   ItemModel itemModel=document.toObject(ItemModel.class);
                   itemlist.add(itemModel);
               }
                Log.d("ItemListFROMCUS", "Fetched data CUS: " + itemlist.size());  // Log the size of the list

                cusItemAdapter.notifyDataSetChanged();

            }
        }) .addOnFailureListener(e -> Log.e("Firestore", "Error fetching data", e));

    }



}