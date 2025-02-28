package com.example.myapplication.Support_clases;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AdapterClases.Orderlist_adapter;
import com.example.myapplication.ModelClass.Order;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Orderlist extends AppCompatActivity {
RecyclerView recyclerView;
Orderlist_adapter adapter;
List<Order>ordermodellist;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orderlist);
        ordermodellist=new ArrayList<>();
fetchdatafromOrderList();
       RecyclerView recyclerView=  findViewById(R.id.orderRecyclerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter=new Orderlist_adapter(this,ordermodellist);
       recyclerView.setAdapter(adapter);



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
                adapter.notifyDataSetChanged();

            }
        });
    }
}