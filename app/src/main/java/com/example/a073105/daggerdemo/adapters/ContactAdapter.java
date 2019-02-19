package com.example.a073105.daggerdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a073105.daggerdemo.R;
import com.example.a073105.daggerdemo.beans.ContactData;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;

    private List<ContactData> mDatas;

    public ContactAdapter(Context context,List<ContactData> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_item, null);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.headImg
        ContactData contactData = mDatas.get(position);

        holder.name.setText(contactData.getName());
        holder.phoneNum.setText(contactData.getPhoneNum());


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

        ImageView headImg;
        TextView name;
        TextView phoneNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             headImg = itemView.findViewById(R.id.contact_headimg);
             name = itemView.findViewById(R.id.contact_name);
             phoneNum = itemView.findViewById(R.id.contact_phone_num);
        }
    }
}
