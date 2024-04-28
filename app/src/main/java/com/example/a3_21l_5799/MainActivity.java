package com.example.a3_21l_5799;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvVault;
    ArrayList<Data> vault;
    DataAdaptor myAdaptor;

    Button btnAdd;
    Button btnSetting;
    String name, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        name = intent.getStringExtra("name");
        pass = intent.getStringExtra("pass");

        init();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,addActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("pass",pass);

                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, settingActivity.class);
                startActivity(intent);
            }
        });


    }
    void init()
    {

        rvVault=findViewById(R.id.rvVault);
        rvVault.setHasFixedSize(true);
        vault=new ArrayList<>();

        btnAdd=findViewById(R.id.btnAdd);
        btnSetting=findViewById(R.id.btnSetting);
        DatabaseHelper database = new DatabaseHelper(this);
        database.open();
        int thisId= database.LoginId(name,pass);

        vault = database.readAllPasswords(thisId);
        database.close();

        rvVault.setLayoutManager(new LinearLayoutManager(this));
        myAdaptor=new DataAdaptor(vault,this,this);
        rvVault.setAdapter(myAdaptor);
    }
}