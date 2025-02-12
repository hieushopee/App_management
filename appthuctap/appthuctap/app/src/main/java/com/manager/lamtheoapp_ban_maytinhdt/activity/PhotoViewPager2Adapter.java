package com.manager.lamtheoapp_ban_maytinhdt.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.lamtheoapp_ban_maytinhdt.R;

import java.util.List;

public class PhotoViewPager2Adapter extends RecyclerView.Adapter<PhotoViewPager2Adapter.PhotoViewHolder>{

    private List<photo> mlistphoto;

    public PhotoViewPager2Adapter(List<photo> mlistphoto) {
        this.mlistphoto = mlistphoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
       photo photo = mlistphoto.get(position);
       if(photo==null){
           return;
       }
       holder.imgPhoto.setImageResource(photo.getResourceId());
    }

    @Override
    public int getItemCount() {
        if(mlistphoto!=null)
            return mlistphoto.size();

        return 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{

         private ImageView imgPhoto;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
