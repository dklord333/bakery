package com.example.myapplication.AdapterClases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.ModelClass.ItemModel;
import com.example.myapplication.Support_clases.ImageUtils;
import com.example.myapplication.databinding.ItemviewverticalBinding;

import java.util.List;

public class admin_item_adapter extends RecyclerView.Adapter<admin_item_adapter.itemviewholder>{
    Context context;
    List<ItemModel> itemModelList;
    public admin_item_adapter(Context context, List<ItemModel> itemModelList) {
        this.context=context;
        this.itemModelList=itemModelList;

    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemviewverticalBinding binding= ItemviewverticalBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
    return new itemviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
        ItemModel itemModel=itemModelList.get(position);
        holder.binding.itemtitle.setText(itemModel.getItemName());
        holder.binding.itemprice.setText(itemModel.getPrice());
        Bitmap decode= ImageUtils.decodeBase64(itemModel.getImage());
        Glide.with(context).load(decode).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                Log.e("Glide", "Image Load Failed", e);
                return false;
            }

            @Override
            public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {


                return false;

            }
        });
    return ;
    }

    @Override
    public int getItemCount() {
      return   itemModelList.size();
    }


    public class itemviewholder extends RecyclerView.ViewHolder {
        ItemviewverticalBinding binding;
        public itemviewholder(@NonNull ItemviewverticalBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
