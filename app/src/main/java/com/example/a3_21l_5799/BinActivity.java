package com.example.a3_21l_5799;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BinActivity extends AppCompatActivity {

    ArrayList<Data> binPasswords;
    RecyclerView rvBin;
    DataAdaptor adaptor;
    Button btnRestore,btnDeleted;
    String username;
    String pass;
    int userid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin);

        Intent intent=getIntent();
        username = intent.getStringExtra("name");
        pass = intent.getStringExtra("pass");

        init();

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDeleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVaultDataPermanently();
            }
        });

    }
    void init()
    {
        btnRestore=findViewById(R.id.btnRestore);
        btnDeleted=findViewById(R.id.btnDelete);
        binPasswords=new ArrayList<>();
        rvBin=findViewById(R.id.rvBin);
        rvBin.setHasFixedSize(true);

//        DatabaseHelper database = new DatabaseHelper(this);
//        database.open();
//        userid= database.LoginId(username,pass);
//
//        binPasswords = database.readAllPasswordsDeleted(userid);
//        database.close();

        rvBin.setLayoutManager(new LinearLayoutManager(this));
        adaptor=new DataAdaptor(binPasswords,this,this);
        rvBin.setAdapter(adaptor);
    }
    void deleteVaultDataPermanently(){
        DatabaseHelper database = new DatabaseHelper(this);
        database.open();
        database.deleteVaultPermanently(userid);
        database.close();
    }
}
