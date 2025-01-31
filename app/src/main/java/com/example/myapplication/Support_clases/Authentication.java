package com.example.myapplication.Support_clases;

import android.content.Context;
import android.support.customtabs.ICustomTabsCallback;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.example.myapplication.Customs.Customs;
import com.example.myapplication.ModelClass.UserModel;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private static FirebaseAuth auth;
    private Context context;
    public Authentication(FirebaseAuth auth,Context context) {
        this.auth = auth;
        this.context = context;


    }

    public void Signup(UserModel userModel){
        String email=userModel.getEmail();
        String password=userModel.getPassword();
        String phoneNo=userModel.getPhoneNo();
        String username=userModel.getUsername();


        if(email == null || email.isEmpty() ||
                password == null || password.isEmpty() ||
                username == null || username.isEmpty() ||
                phoneNo == null || phoneNo.isEmpty()){
            Customs customs=new Customs(context);


            customs.showAlert("Failed","Fill the Form Completely");
            return;
        }
        if(!Validations.isemailvalid(email)){
            Customs customs=new Customs(context);
            customs.showAlert("Failed","Please enter a valid email address");
            return;

        }
        if(!Validations.ispasswordvalid(password)){
            Customs customs=new Customs(context);
            customs.showAlert("Failed","Please enter a valid password");
            return;
        }
        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(task -> {
                     {
                         try {
                             FirebaseUser user =task.getUser();
                             if(user != null){

                                 AddUserToFirestore(user,email,password,phoneNo,username);
                             }

                         }
                         catch (Exception e){
                             Customs customs=new Customs(context);
                             customs.showAlert(e.getMessage(),"some error");
                         }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Customs customs=new Customs(context);
                        customs.showAlert(e.getMessage(),"some error");

                    }
                });


    }

    private static void AddUserToFirestore(FirebaseUser user, String email, String password, String phoneNo, String username) {
String userid=user.getUid();
        Map<Object,String> userdata=new HashMap<>();
        userdata.put("uid",userid);
        userdata.put("email",email);
        userdata.put("password",password);
        userdata.put("phoneNo",phoneNo);
        userdata.put("username",username);
        DocumentReference firestore;
        FirebaseFirestore.getInstance().collection("users").document(userid).set(userdata);
    }

    private class AuthCallback {

    }
}
