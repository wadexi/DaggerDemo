package com.android.wadexi.basedemo.architecture.ui.adapters.cookmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.glide.setting.GlideApp;
import com.android.wadexi.basedemo.views.ResizableImageView;

import java.util.List;

public class CookMenuStepAdapter extends RecyclerView.Adapter<CookMenuStepAdapter.ViewHolder> {


    private Context mContext;
    private Fragment fragment;
    private LayoutInflater mInflater;

    private List<CookBookBean.DataBean.StepsBean> mDatas;

    public CookMenuStepAdapter(Fragment fragment, List<CookBookBean.DataBean.StepsBean> datas) {
        this.fragment = fragment;
        this.mContext = fragment.getActivity().getApplicationContext();
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cook_menu_steps_item, null);
        itemView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CookBookBean.DataBean.StepsBean stepsBean = mDatas.get(position);
        holder.stepIntro.setText(stepsBean.getStep());
        GlideApp.with(fragment).load(stepsBean.getImg())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.stepImg);


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

        TextView stepIntro;
        ResizableImageView stepImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepIntro = itemView.findViewById(R.id.step_intro);
            stepImg = itemView.findViewById(R.id.step_img);
        }
    }
}
