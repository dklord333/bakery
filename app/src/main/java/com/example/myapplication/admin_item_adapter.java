package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemviewverticalBinding;

public class admin_item_adapter extends RecyclerView.Adapter<admin_item_adapter.itemviewholder>{
    public admin_item_adapter() {
        Context context;

    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemviewverticalBinding binding= ItemviewverticalBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
    return new itemviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
    return ;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class itemviewholder extends RecyclerView.ViewHolder {
        ItemviewverticalBinding binding;
        public itemviewholder(@NonNull ItemviewverticalBinding binding) {
            super(binding.getRoot());
        }
    }
}
