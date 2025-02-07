package com.example.myapplication.AdapterClases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class cus_item_adapter extends RecyclerView.Adapter<cus_item_adapter.itemviewholder> {
 Context context;
    private List<ItemModel> itemList;

    public cus_item_adapter(Context context, List<ItemModel> itemList) {
      this.context=context;
      this.itemList=itemList;

    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemviewverticalBinding binding=ItemviewverticalBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return  new itemviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
        
        ItemModel itemModel=itemList.get(position);
        holder.binding.itemprice.setText(itemModel.getPrice());
        holder.binding.itemtitle.setText(itemModel.getItemName());
        String base64Image = itemModel.getImage();  // Assuming `getImage()` returns the Base64 string
        Bitmap decodedImage = ImageUtils.decodeBase64(base64Image);


       Glide.with(context).load(decodedImage).listener(new RequestListener<Drawable>() {
           @Override
           public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
               Log.e("Glide", "Image Load Failed", e);
               return false;
           }

           @Override
           public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
               Log.d("Glide", "Image Loaded Successfully");

               return false;
           }
       }).into(holder.binding.Itemimage);

    }

    @Override
    public int getItemCount() {
        Log.d("ItemList", "Fetched data: " + itemList.size());  // Log the size of the list

        return itemList.size();


    }

    public class itemviewholder extends RecyclerView.ViewHolder {
        ItemviewverticalBinding binding;
        public itemviewholder(ItemviewverticalBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
