package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityCustomerDashboardBinding;

public class customer_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityCustomerDashboardBinding binding=ActivityCustomerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Spinner spinner=binding.dropdown;
        String[]  spinnerItems={"Select Category","Cakes","Biscuits","Brownies","Baked Items","Dougnuts, Sundas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems) {
            @Override
            public boolean isEnabled(int position) {
                // Disable the hint option
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // Change color of hint item (optional)
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    TextView tv = (TextView) view;

                }
                return view;
            }
        };

// Set the layout resource for spinner dropdown items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Set the adapter on the spinner
        spinner.setAdapter(adapter);
    }
}