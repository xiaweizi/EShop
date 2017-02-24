package com.xiaweizi.eshop.feature.category;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaweizi.eshop.R;
import com.xiaweizi.eshop.network.entity.CategoryPrimary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryPrimary> mDatas = new ArrayList<>();

    // 多外提供一个方法
    public void reset(List<CategoryPrimary> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CategoryPrimary getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_primary_category, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        holder.textCategory.setText(mDatas.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.text_category)
        TextView textCategory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
