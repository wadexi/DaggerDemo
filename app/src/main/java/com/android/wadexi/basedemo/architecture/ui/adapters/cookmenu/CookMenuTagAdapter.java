package com.android.wadexi.basedemo.architecture.ui.adapters.cookmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wadexi.basedemo.R;

import java.util.List;

public class CookMenuTagAdapter extends RecyclerView.Adapter<CookMenuTagAdapter.ViewHolder> {


    private Context mContext;
    private Fragment fragment;
    private LayoutInflater mInflater;

    private List<String> mDatas;

    public CookMenuTagAdapter(Fragment fragment, List<String> datas) {
        this.fragment = fragment;
        this.mContext = fragment.getActivity().getApplicationContext();
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cook_menu_tag_item, null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tag = mDatas.get(position);
        holder.tag.setText(tag);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {

        //此处可以从构造函数（new 的是什么类型的adapter）里获取到viewType类型
        return super.getItemViewType(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag_tv);
        }
    }
}
