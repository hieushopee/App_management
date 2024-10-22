package com.manager.lamtheoapp_ban_maytinhdt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.lamtheoapp_ban_maytinhdt.Interface.ItemClickListener;
import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.activity.ChiTietActivity;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class DoAnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<SanPhamMoi> array;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;




    public DoAnAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_DATA){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doan, parent, false);
                return new MyViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder)holder;
            SanPhamMoi sanpham = array.get(position);
            myViewHolder.tensp.setText(sanpham.getTensp().trim());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.giasp.setText("Giá:" + decimalFormat.format(Double.parseDouble(sanpham.getGiasp()))+"Đ");
            myViewHolder.mota.setText(sanpham.getMota());

            if(sanpham.getHinhanh().contains("http")){
                Glide.with(context).load(sanpham.getHinhanh()).into(((MyViewHolder) holder).hinhanh);
            }else {
                String hinh = Utils.BASE_URL+"images/"+sanpham.getHinhanh();
                Glide.with(context).load(hinh).into(((MyViewHolder) holder).hinhanh);
            }

//            Glide.with(context).load(sanpham.getHinhanh()).into(myViewHolder.hinhanh);
            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        //click
                        Intent intent = new Intent(context, ChiTietActivity.class);
                        intent.putExtra("chitiet", sanpham);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else{
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tensp, giasp, mota;
        ImageView hinhanh;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView){
            super(itemView);
            tensp = itemView.findViewById(R.id.itemdt_ten);
            giasp = itemView.findViewById(R.id.itemdt_gia);
            mota = itemView.findViewById(R.id.itemdt_mota);
            hinhanh = itemView.findViewById(R.id.itemdt_image);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{

        ProgressBar progressBar;
        public LoadingViewHolder(View itemview){
            super(itemview);
            progressBar = itemview.findViewById(R.id.progressbar);
        }
    }
}
