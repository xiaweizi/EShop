package com.xiaweizi.eshop.feature.category;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaweizi.eshop.R;
import com.xiaweizi.eshop.network.entity.CategoryBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 子分类的适配器
 */
public class ChildrenAdapter extends BaseAdapter {

    private List<CategoryBase> mDatas = new ArrayList<>();

    public void reset(List<CategoryBase> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CategoryBase getItem(int position) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_children_category, null);
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


//    @Override protected int getItemViewLayout() {
//        return R.layout.item_children_category;
//    }

//    @Override protected ViewHolder getItemViewHolder(View itemView) {
//        return new ViewHolder(itemView);
//    }
//
//    class ViewHolder extends BaseListAdapter.ViewHolder {
//
//        @BindView(R.id.text_category) TextView tvCategory;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        @Override protected void bind(int position) {
//            tvCategory.setText(getItem(position).getName());
//        }
//    }
}
