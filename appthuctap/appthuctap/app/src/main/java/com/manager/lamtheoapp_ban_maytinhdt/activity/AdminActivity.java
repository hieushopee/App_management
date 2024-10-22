package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manager.lamtheoapp_ban_maytinhdt.R;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {

    Button them, thongke, trangchu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
        initStart();
    }

    private void initStart() {
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), QuanLiActivity.class);
                startActivity(in);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent in1 = new Intent(getApplicationContext(), ThongKeActivity.class);
                    startActivity(in1);
            }
        });
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in2);
            }
        });
    }

    private void initView() {
        them = findViewById(R.id.them);
        thongke = findViewById(R.id.thongke);
        trangchu = findViewById(R.id.trangchu);
    }
}