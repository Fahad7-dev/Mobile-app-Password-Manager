package com.example.a3_21l_5799;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class settingActivity extends AppCompatActivity {
    Button btnRecyclerBin, btnLogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Intent intent=getIntent();
        String username = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        init();

        btnRecyclerBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(settingActivity.this,BinActivity.class);
                intent.putExtra("name",username);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(settingActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void init()
    {
        btnRecyclerBin=findViewById(R.id.btnRecycleBin);
        btnLogout=findViewById(R.id.btnLogout);
    }
}

