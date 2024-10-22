package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.GioHangAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.model.EvenBus.TinhTongEvent;
import com.manager.lamtheoapp_ban_maytinhdt.model.GioHang;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {

    TextView giohangtrong, tongtien;
    Toolbar toolbar12;
    RecyclerView recyclerView;
    Button btnmuahang;
    GioHangAdapter adapter;
    List<GioHang> gioHangList;
    long tongtiensp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        initView();
        initConTrol();
        tinhTongTien();
    }

    private void tinhTongTien() {
        tongtiensp = 0;
        for(int i = 0; i<Utils.manggiohang.size(); i++){
            tongtiensp = tongtiensp+(Utils.manggiohang.get(i).getGiasp() * Utils.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtiensp));
    }

    private void initConTrol() {
        setSupportActionBar(toolbar12);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar12.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(Utils.manggiohang.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);

        }
        else {
            adapter = new GioHangAdapter(getApplicationContext(), Utils.manggiohang);
            recyclerView.setAdapter(adapter);
        }

        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                intent.putExtra("tongtien", tongtiensp);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        giohangtrong = findViewById(R.id.txtgiohangtrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar12 = findViewById(R.id.toolbar12);
        recyclerView = findViewById(R.id.recyclerviewgiohang);
        btnmuahang = findViewById(R.id.btnmuahang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if (event != null){
            tinhTongTien();
        }
    }
}