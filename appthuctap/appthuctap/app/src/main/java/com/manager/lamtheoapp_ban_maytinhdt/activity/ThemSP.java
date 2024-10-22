package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.databinding.ActivityThemSpBinding;
import com.manager.lamtheoapp_ban_maytinhdt.model.MessageModel;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
//import okhttp2.Call;
import retrofit2.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
//import okhttp3.Response;
import retrofit2.Call;

public class ThemSP extends AppCompatActivity {
    Spinner spinner;
    int loai = 0;
    ActivityThemSpBinding binding;
    ApiBanhang apiBanhang ;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    String mediaPath;
    SanPhamMoi sanPhamSua;
    boolean flaag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemSpBinding.inflate(getLayoutInflater());
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        setContentView(binding.getRoot());
        initView();
        initControl();
        initData();
        Intent intent = getIntent();
        sanPhamSua = (SanPhamMoi) intent.getSerializableExtra("sua");
        if(sanPhamSua==null){
            //themmoi
            flaag = false;
        }
        else{
            //sua
            flaag = true;
            binding.btnthem.setText("Sửa sản phẩm");
            binding.tensp.setText(sanPhamSua.getTensp());
            binding.giasp.setText(sanPhamSua.getGiasp()+"");
            binding.hinhanh.setText(sanPhamSua.getHinhanh());
            binding.mota.setText(sanPhamSua.getMota());
//            binding.spinnerLoai.setSelection(sanPhamSua.getLoai());
        }

    }

    private void initData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Vui lòng chọn loại");
        stringList.add("Đồ ăn");
        stringList.add("Đồ uống");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, io.paperdb.R.layout.support_simple_spinner_dropdown_item,stringList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loai = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flaag==false){
                    themsanpham();
                } else{
                    suaSanPham();
                }

            }
        });

        binding.imgcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(ThemSP.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }

    private void suaSanPham() {
        String str_ten = binding.tensp.getText().toString().trim();
        String str_gia = binding.giasp.getText().toString().trim();
        String str_mota = binding.mota.getText().toString().trim();
        String hinhanh = binding.hinhanh.getText().toString().trim();
        if(TextUtils.isEmpty(str_ten) || TextUtils.isEmpty(str_gia) || TextUtils.isEmpty(str_mota) ||TextUtils.isEmpty(hinhanh) || loai == 0){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            compositeDisposable.add(apiBanhang.updatesp(str_ten, str_gia, hinhanh, str_mota, loai, sanPhamSua.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if(messageModel.isSuccess()){
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                    ));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mediaPath = data.getDataString();
        uploadMultipleFiles();
        Log.d("log", "onActivityResult: " + mediaPath);
    }

    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor == null){
            result = uri.getPath();
        }else{
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }

    private void themsanpham() {
        String str_ten = binding.tensp.getText().toString().trim();
        String str_gia = binding.giasp.getText().toString().trim();
        String str_mota = binding.mota.getText().toString().trim();
        String hinhanh = binding.hinhanh.getText().toString().trim();
        if(TextUtils.isEmpty(str_ten) || TextUtils.isEmpty(str_gia) || TextUtils.isEmpty(str_mota) ||TextUtils.isEmpty(hinhanh) || loai == 0){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            compositeDisposable.add(apiBanhang.insertSp(str_ten, str_gia, hinhanh, str_mota, loai)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if(messageModel.isSuccess()){
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent a = new Intent(getApplicationContext(), QuanLiActivity.class);
                                    startActivity(a);
                                }else{
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                    ));
        }

    }

    private void initControl() {

    }

    private void initView() {
        spinner  = findViewById(R.id.spinner_loai);

    }


    private void uploadMultipleFiles() {
        Uri uri = Uri.parse(mediaPath);
        File file = new File(getPath(uri));
        // Map is used to multipart the file using okhttp3.RequestBody


        // Parsing any Media type file
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
        Call<MessageModel> call = apiBanhang.uploadFile(fileToUpload1);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call <MessageModel> call, Response<MessageModel> response) {
                MessageModel serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.isSuccess()) {
                        binding.hinhanh.setText(serverResponse.getName());
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.v("Response", serverResponse.toString());
                }
            }
            @Override
            public void onFailure(Call <MessageModel> call, Throwable t) {
                Log.d("log", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}