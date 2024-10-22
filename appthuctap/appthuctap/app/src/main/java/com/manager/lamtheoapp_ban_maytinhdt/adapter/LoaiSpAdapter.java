package com.manager.lamtheoapp_ban_maytinhdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.model.LoaiSp;

import java.util.List;

public class LoaiSpAdapter extends BaseAdapter{

    List<LoaiSp>  array;
    Context context;

    public LoaiSpAdapter( Context context, List<LoaiSp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHodel{
        TextView texttensp;
        ImageView imghinhanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodel viewHodel = null;
        if(view == null){
            viewHodel = new ViewHodel();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHodel.texttensp = view.findViewById(R.id.item_sanpham);
            viewHodel.imghinhanh = view.findViewById(R.id.item_image);
            view.setTag(viewHodel);

        }
        else {
            viewHodel = (ViewHodel) view.getTag();


        }
        viewHodel.texttensp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHodel.imghinhanh);
        return view;
    }
}
