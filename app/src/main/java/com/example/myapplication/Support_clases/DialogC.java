package com.example.myapplication.Support_clases;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.Customs.Customs;
import com.example.myapplication.ModelClass.ItemModel;
import com.example.myapplication.ModelClass.Order;
import com.example.myapplication.databinding.OrderpageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DialogC {
    List<ItemModel>itemModelList;

    public DialogC(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public void showorderpage(Context context,int position){
        if (itemModelList == null || itemModelList.isEmpty() || position >= itemModelList.size()) {
            Toast toast=Toast.makeText(context,"Nothing to show",Toast.LENGTH_SHORT);
            toast.show();
        }
        ItemModel itemModel=itemModelList.get(position);

        OrderpageBinding binding=OrderpageBinding.inflate(LayoutInflater.from(context));

        android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
     binding.descriptiontext.setText(itemModel.getDescription());


        String base64Image = itemModel.getImage();  // Assuming `getImage()` returns the Base64 string
        Bitmap decode = ImageUtils.decodeBase64(base64Image);
        if(base64Image != null ){

            Glide.with(context).load(decode).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                    Log.d("Image Load Failed", "onLoadFailed: Image Load Failed");
                    return false;
                }

                @Override
                public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(binding.image);
            binding.cancelDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
binding.place0rder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String address= binding.address.getText().toString();
        String name=binding.name.getText().toString();
        String phoneno=binding.num.getText().toString();
        String quantity= String.valueOf(binding.np.getValue());
        String itemname=itemModel.getItemName();
        String itemprice=itemModel.getPrice();
        String image=itemModel.getImage();
        FirebaseUser user;
        Log.d("np Value", "onClick: np value us" +quantity);
        Order order= new Order(itemname,phoneno,address,quantity,itemprice,image);
        StoreOrdertofirebase(context,order);

    }
});
        }


        if (dialog.getWindow() != null) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (int) (displayMetrics.widthPixels * 0.93);
            int height = (int) (displayMetrics.heightPixels * 0.93);
            dialog.getWindow().setLayout(width, height);
        }


     dialog.show();

    }


    private void StoreOrdertofirebase(Context context,Order order) {
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        long date=Customs.DateTime();
        Customs customs=new  Customs(context);
        FirebaseFirestore.getInstance().collection("OrderInfo").document(String.valueOf(date)).set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                     @Override
                                                                                                                     public void onSuccess(Void unused) {
                                                                                                                         customs.showAlert("Success","Order Added ");
                                                                                                                     }
                                                                                                                 }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                customs.showAlert("Sorry","Order is not Added");
            }
        });

    }



}
