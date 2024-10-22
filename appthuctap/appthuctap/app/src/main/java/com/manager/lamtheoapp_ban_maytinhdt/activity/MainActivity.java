package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.LoaiSpAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.SanPhamMoiAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.model.LoaiSp;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.model.User;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;


import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
//    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;

    private ViewPager2 mviewPager2;
    private CircleIndicator3 mcircleIndicator3;
    private List<photo> mlistphoto;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaiSp;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;

    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;

    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;



    private Handler mhandler = new Handler();
    private Runnable mrunRunnable = new Runnable() {
        @Override
        public void run() {
            if (mviewPager2.getCurrentItem() == mlistphoto.size() - 1) {
                mviewPager2.setCurrentItem(0);
            } else {
                mviewPager2.setCurrentItem(mviewPager2.getCurrentItem() + 1);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);

        Paper.init(this);
        if(Paper.book().read("user")!=null){
            User user = Paper.book().read("user");
            Utils.user_current = user;

            User user1 = Paper.book().read("user3");
            Utils.user_current1 = user1;
        }
        Anhxa();
        ActionBar();
        slide();


        if(isConnected(this)){
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }
        else{
            Toast.makeText(this, "không có kết nối internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEventClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(), DoAnActivity.class);
                        dienthoai.putExtra("loai", 1);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(), DoAnActivity.class);
                        laptop.putExtra("loai", 2);
                        startActivity(laptop);
                        break;

                    case 4:
                        Intent lienhe = new Intent(getApplicationContext(), LienHeActivity.class);
                        startActivity(lienhe);
                        break;

                    case 5:
                        Intent donhang = new Intent(getApplicationContext(), XemDonActivity.class);
                        startActivity(donhang);
                        break;

                    case 6:
                        Intent chat = new Intent(getApplicationContext(), ChatGPTActivity.class);
                        startActivity(chat);
                        break;




                    case 7:
                        // xóa key user
                        Paper.book().delete("user");
                        Intent dangxuat = new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangxuat);
                        finish();
                        break;




                }

            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanhang.getSqMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSucess()){
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSpMoi);
                                recyclerView.setAdapter(spAdapter);


                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không thể kêt nối ssever"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanhang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSucess()){
                                mangloaiSp = loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), mangloaiSp);
                                listView.setAdapter(loaiSpAdapter);


                            }
                            else
                                Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();

                        }
                ));

    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        imgsearch = findViewById(R.id.imgsearch);
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        listView = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        recyclerView = findViewById(R.id.recycleview);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mviewPager2 = findViewById(R.id.viewpager2);
        mcircleIndicator3 = findViewById(R.id.circle_indacator3);

        mangloaiSp = new ArrayList<>();

        mangSpMoi = new ArrayList<>();
        if(Utils.manggiohang==null){
            Utils.manggiohang = new ArrayList<>();
        }
        else{

                int totalItem = 0;
                for(int i =0; i<Utils.manggiohang.size(); i++){
                    totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
                }
                badge.setText(String.valueOf(totalItem));
        }

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);

            }
        });

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean isConnected(Context context) {

//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if(ni != null && ni.isConnected()) {
                return true;
            }
            return false;
        }

    private void slide(){
        mlistphoto = getlistphoto();
        PhotoViewPager2Adapter adapter = new PhotoViewPager2Adapter(mlistphoto);

        mviewPager2.setAdapter(adapter);

        mcircleIndicator3.setViewPager(mviewPager2);

        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {


                super.onPageSelected(position);
                mhandler.removeCallbacks(mrunRunnable);
                mhandler.postDelayed(mrunRunnable, 3000);
            }

        });
    }

    private List<photo> getlistphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.ver2));
        list.add(new photo(R.drawable.pizza1));
        list.add(new photo(R.drawable.pizza2));
        list.add(new photo(R.drawable.pizza3));

        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mhandler.removeCallbacks(mrunRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mhandler.postDelayed(mrunRunnable, 3000);

        int totalItem = 0;
        for(int i =0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}