package com.example.tecknet.view;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Controller {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public Controller(){
        connect_to_firebase();
    }

    private void connect_to_firebase(){
        rootNode = FirebaseDatabase.getInstance(); //connect to firebase
        reference = rootNode.getReference("users");
    }
    public void add_to_database(String fNames ,String lNames ,String emailS
                               ,String role, String passwordS ,String phoneS ){

        UserHelperClass helper = new UserHelperClass(fNames , lNames ,passwordS , emailS , phoneS);
        reference.child(phoneS).setValue(helper);

    }
    public boolean  check_if_user_exist(String phoneNum){
        return false;
    }


}
