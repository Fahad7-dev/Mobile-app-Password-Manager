package com.example.a3_21l_5799;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignup;
    EditText etNameSign, etPassSign, etRePassSign, etEmailSign, etPhoneSign;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        init();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();

                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected void init() {
        btnSignup = findViewById(R.id.btnSignup);
        etNameSign = findViewById(R.id.etNameSign);
        etPassSign = findViewById(R.id.etPassSign);
        etRePassSign = findViewById(R.id.etRePassSign);
        etEmailSign = findViewById(R.id.etEmailSign);
        etPhoneSign=findViewById(R.id.etPhoneSign);
    }
    protected void SignUp()
    {
        String name = etNameSign.getText().toString().trim();
        String pass = etPassSign.getText().toString();
        String repass = etRePassSign.getText().toString();
        String email = etEmailSign.getText().toString();
        String phone = etPhoneSign.getText().toString();

        DatabaseHelper myDatabaseHelper = new DatabaseHelper(this);
        myDatabaseHelper.open();

        if(!pass.equals(repass)) {
            Toast.makeText(this, "Password is not equal to Re-entered password", Toast.LENGTH_SHORT).show();
        }
        else{
            myDatabaseHelper.UserRegisteration(name,pass,email,phone);
            myDatabaseHelper.close();
        }

    }
}
