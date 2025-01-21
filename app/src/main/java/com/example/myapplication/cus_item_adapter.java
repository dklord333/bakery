package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemviewverticalBinding;

public class cus_item_adapter extends RecyclerView.Adapter<cus_item_adapter.itemviewholder> {
    public cus_item_adapter() {
        Context context;
    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemviewverticalBinding binding=ItemviewverticalBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return  new itemviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class itemviewholder extends RecyclerView.ViewHolder {
        ItemviewverticalBinding binding;
        public itemviewholder(ItemviewverticalBinding binding) {
            super(binding.getRoot());
        }
    }
}
