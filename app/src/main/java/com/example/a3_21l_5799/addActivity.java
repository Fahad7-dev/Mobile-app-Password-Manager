package com.example.a3_21l_5799;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {

    EditText etNameAdd, etPassAdd, etUrlAdd;
    Button btnAddDetails;
    String name,pass;
    int userID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);



        init();


        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData();

                Intent intent = new Intent(addActivity.this, MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("pass",pass);
                startActivity(intent);
                finish();
            }
        });

    }
    void init()
    {

        Intent intent=getIntent();
        name = intent.getStringExtra("name");
        pass=intent.getStringExtra("pass");

        etNameAdd=findViewById(R.id.etNameAdd);
        etPassAdd=findViewById(R.id.etPassAdd);
        etUrlAdd=findViewById(R.id.etUrlAdd);

        btnAddDetails=findViewById(R.id.btnAddDetails);
    }
    void addData(){
        String username= etNameAdd.getText().toString();
        String password= etPassAdd.getText().toString();
        String url= etUrlAdd.getText().toString();

        DatabaseHelper myDatabaseHelper = new DatabaseHelper(this);
        myDatabaseHelper.open();
        userID= myDatabaseHelper.LoginId(name,pass);

        myDatabaseHelper.insertVaultData(username, password,url, userID);

        myDatabaseHelper.close();

    }
}

