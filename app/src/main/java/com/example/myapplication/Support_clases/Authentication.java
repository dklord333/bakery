package com.example.myapplication.Support_clases;

import android.app.AlertDialog;
import android.hardware.biometrics.BiometricPrompt;

import com.example.myapplication.Customs.Customs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.rpc.context.AttributeContext;

public class Authentication {
    private final FirebaseAuth auth;
    public Authentication(FirebaseAuth auth) {
        this.auth = auth;

    }

    public void Signup(String username, String email, String password, String phoneNo, AuthCallback callback){
        if(!Validations.isemailvalid(email)){
           Customs.showAlert("Failed","Please enter a valid email address");

        }
        if(!Validations.ispasswordvalid(password)){
            Customs.showAlert("Failed","Please enter a valid password");
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(task -> {
                     {


                    }
                });


    }

    private class AuthCallback {

    }
}
