package com.example.myapplication.AdapterClases;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.ModelClass.Order;
import com.example.myapplication.R;
import com.example.myapplication.Support_clases.ImageUtils;
import com.example.myapplication.Support_clases.Orderlist;
import com.example.myapplication.databinding.OrderpageBinding;
import com.example.myapplication.databinding.OrderviewBinding;

import java.util.List;

public class Orderlist_adapter extends RecyclerView.Adapter<Orderlist_adapter.itemviewholder> {
    List<Order> Ordermodellist;
    Context context;

    public Orderlist_adapter(Context context, List<Order> ordermodellist) {
        this.Ordermodellist = ordermodellist;
        this.context = context;
    }

    @NonNull
    @Override
    public Orderlist_adapter.itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderviewBinding binding = OrderviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new itemviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Orderlist_adapter.itemviewholder holder, int position) {


        Order ordermodel = Ordermodellist.get(position);
        String itemname = ordermodel.getItemname();
        String itemquantity = ordermodel.getQuantity();
        String orderaddress = ordermodel.getAddress();


        holder.binding.itemtitle.setText(itemname);
        holder.binding.Quantity.setText(itemquantity);
        holder.binding.OrderAdress.setText(orderaddress);
        String base64 = ordermodel.getImage();
        Log.d(" Check data in ordermodel list", "Data in ordermodel: " + base64);
        if (base64 != null && !base64.isEmpty()) {
            Bitmap decode = ImageUtils.decodeBase64(base64);
            Glide.with(context).load(decode).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                    Log.d("OrdervieewImage", "onLoadFailed: " + e);
                    return false;
                }

                @Override
                public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                    Log.d("OrdervieewImage", "onLoaded: ");
                    return false;
                }
            }).into(holder.binding.Itemimage);
        } else {
            Log.e("Orderlist_adapter", "Base64 image string is null or empty");
            holder.binding.Itemimage.setImageResource(R.drawable.dp); // Set a default image
        }
    }

        @Override
    public int getItemCount() {
        return Ordermodellist.size();
    }

    public class itemviewholder extends RecyclerView.ViewHolder {
        OrderviewBinding binding;
        public itemviewholder(@NonNull OrderviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }
}
