package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityCustomerDashboardBinding;

public class customer_dashboard extends AppCompatActivity {

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

       RecyclerView recyclerView=findViewById(R.id.Cus_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        cus_item_adapter cusItemAdapter=new cus_item_adapter();
        recyclerView.setAdapter(cusItemAdapter);
    }




}