package com.example.tecknet.view;
import com.example.tecknet.model.*;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tecknet.R;

public class SignUpActivity extends AppCompatActivity {

    private UserViewModel uViewModel;

    EditText email ,fName ,lName ,pass ,phone;
    Spinner role;
    Button next;

    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.email);
        fName = (EditText) findViewById(R.id.first_name);
        lName = (EditText) findViewById(R.id.last_name);
        pass = (EditText) findViewById(R.id.pass);
        role = (Spinner) findViewById(R.id.role);
        phone = (EditText) findViewById(R.id.phone);
        next = (Button) findViewById(R.id.button) ;

        pBar = findViewById(R.id.progressBar);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fNames = fName.getText().toString();
                String lNames = lName.getText().toString();
                String emailS = email.getText().toString();
                String roleS = role.getSelectedItem().toString();
                String passwordS = pass.getText().toString();
                String phoneS = phone.getText().toString();

                boolean ans = check_if_entered_details(fNames,lNames ,emailS,roleS,passwordS, phoneS);

                if(ans) {
                    boolean valid = is_valid_detaild(emailS,phoneS);
                    if(valid) {
                        pBar.setVisibility(View.VISIBLE);//loading

                        User myUser = new User(fNames, lNames, passwordS, emailS, roleS, phoneS);

//                    //add user to shared view model//todo erase
//                    uViewModel = new ViewModelProvider(SignUpActivity.this).get(UserViewModel.class);
//                    uViewModel.setItem(myUser);

//                  todo  check_if_user_exist(phoneS);

                        // call to new_user from controller hoe enter the new details of the user
                        com.example.tecknet.model.Controller.new_user(fNames, lNames, phoneS, emailS, passwordS, roleS);
                        clear_from_editext(); // clear from the edit text

                        if (roleS.equals("???? ??????")) continue_to_institution_detail(myUser);
                        else continue_to_tech_detail(myUser);
                    }

                }
            }
        });
    }

    /**
     * this function is private for check if the User enter his details
     * @param fNames
     * @param lNames
     * @param emailS
     * @param passwordS
     * @param phoneS
     * @return true if the User enter all the needed details
     *         false if some detail is missing
     */
    private boolean check_if_entered_details(String fNames ,String lNames ,String emailS,
                                         String roleS,String passwordS ,String phoneS ){
        if(emailS.isEmpty()){
            email.setError("?????? ???????? ?????????? ????????");
            return false;
        }
        if(fNames.isEmpty()){
            fName.setError("?????? ???????? ???? ????????");
            return false;
        }
        if(lNames.isEmpty()){
            lName.setError("?????? ???????? ???? ??????????");
            return false;
        }
        if(passwordS.isEmpty()){
            pass.setError("?????? ???????? ??????????");
            return false;
        }
        if(phoneS.isEmpty()){
            phone.setError("?????? ???????? ???????? ??????????");
            return false;
        }
        if(roleS.equals("??????")){
            TextView errorText = (TextView)role.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("?????? ?????? ??????????");//changes the selected item text to this
            return false;
        }
        return true;


    }

    /**
     * this function check if the phone number and mail address is valide
     * @param phoneS
     * @param emailS
     * @return true if phone number & email is valid
     *      else false
     */
    private boolean is_valid_detaild(String emailS ,String phoneS ){

        if(!com.example.tecknet.model.ValidInputs.valid_email(emailS)){
            email.setError("?????????? ???????? ???? ????????!");
            return false;

        }
        if(!com.example.tecknet.model.ValidInputs.valid_phone(phoneS)){
            phone.setError("???????? ?????????? ???? ????????!");
            return false;

        }
        return true;
    }


    /**
     * this function move to the next screen
     */
    private void continue_to_institution_detail( User myUser) {
        Intent intent = new Intent(SignUpActivity.this, MaintenanceManDetailsActivity.class);
        intent.putExtra("User" ,  myUser);
        startActivity(intent);
    }
    private void continue_to_tech_detail( User myUser) {
        Intent intent = new Intent(SignUpActivity.this, TechMenDetailsActivity.class);
        intent.putExtra("User" , myUser);
        startActivity(intent);
    }

    /**
     * Private function how clear the text from the edit text view
     */
    private void clear_from_editext(){
        email.getText().clear();
        fName.getText().clear();
        lName.getText().clear();
        pass.getText().clear();
        phone.getText().clear();
    }
}