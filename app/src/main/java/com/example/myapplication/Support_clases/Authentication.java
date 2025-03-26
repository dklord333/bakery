package com.example.myapplication.Support_clases;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.customtabs.ICustomTabsCallback;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.example.myapplication.Admin_Dashboard;
import com.example.myapplication.Customs.Customs;
import com.example.myapplication.LoginFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.ModelClass.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.customer_dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

    };
    public void Signin(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            Customs customs = new Customs(context);
            customs.showAlert("Error", "Email and password cannot be empty");
            Intent intent = new Intent(context, Admin_Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return;

        }
        Log.d("Authentication", "Attempting to sign in with email: " + email);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Authentica", "Sign-in-Started");
                        if (task.isSuccessful()) {
                            Log.d("Authentication", "Sign-in successful");
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                String userid = user.getUid();
                                String useremail = user.getEmail();
                                Log.d("Authentication", "User ID: " + userid + ", Email: " + useremail);

                                // Navigate to Admin_Dashboard
                                Intent intent = new Intent(context, customer_dashboard.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        } else {
                            Log.e("Authentication", "Sign-in failed", task.getException());
                            Customs customs = new Customs(context);
                            customs.showAlert("Failed", "Email or Password is not valid");
                        }
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
