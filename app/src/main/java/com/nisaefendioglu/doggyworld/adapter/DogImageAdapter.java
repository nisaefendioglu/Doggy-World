package com.nisaefendioglu.doggyworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nisaefendioglu.doggyworld.R;

import java.util.List;

public class DogImageAdapter extends BaseAdapter {

    private Context context;
    private List<String> dogImagesList;

    public DogImageAdapter(Context context, List<String> dogImagesList) {
        this.context = context;
        this.dogImagesList = dogImagesList;
    }

    @Override
    public int getCount() {
        return dogImagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return dogImagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_dog_image, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = dogImagesList.get(position);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .fitCenter()
                .into(holder.imageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
