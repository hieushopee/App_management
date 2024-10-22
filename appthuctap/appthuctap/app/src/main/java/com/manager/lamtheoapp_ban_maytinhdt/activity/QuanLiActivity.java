package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.SanPhamMoiAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.model.EvenBus.SuaXoa;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuanLiActivity extends AppCompatActivity {

    ImageView img_them;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;
    List<SanPhamMoi> list;
    SanPhamMoiAdapter adapter;
    SanPhamMoi sanPhamSuaXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li);
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        initView();
        initControl();
        getSpMoi();
    }

    private void initControl() {
        img_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemSP.class);
                startActivity(intent);
            }
        });
    }

    public void getSpMoi() {
        compositeDisposable.add(apiBanhang.getSqMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSucess()){
                                list = sanPhamMoiModel.getResult();
                                adapter = new SanPhamMoiAdapter(getApplicationContext(), list);
                                recyclerView.setAdapter(adapter);


                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không thể kêt nối ssever"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    private void initView() {
        img_them = findViewById(R.id.img_them);
        recyclerView = findViewById(R.id.recycleview_ql);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Sửa")){
            suaSanPham();
        }
        else if(item.getTitle().equals("Xóa")){
            xoaSanPham();
        }

        return super.onContextItemSelected(item);
    }

    private void xoaSanPham() {
        compositeDisposable.add(apiBanhang.xoaSanPham(sanPhamSuaXoa.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messageModel -> {
                            if(messageModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                getSpMoi();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            Log.d("log", throwable.getMessage());
                        }
                ));
    }

    private void suaSanPham() {

        Intent in = new Intent(getApplicationContext(), ThemSP.class);
        in.putExtra("sua", sanPhamSuaXoa);
        startActivity(in);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void evenSuaXoa(SuaXoa event){
        if(event != null){
            sanPhamSuaXoa = event.getSanPhamMoi();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}