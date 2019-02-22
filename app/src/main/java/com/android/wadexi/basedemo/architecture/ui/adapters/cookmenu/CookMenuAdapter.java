package com.android.wadexi.basedemo.architecture.ui.adapters.cookmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.recyclerview.RecycleViewDivider;

import java.util.Arrays;
import java.util.List;

public class CookMenuAdapter extends RecyclerView.Adapter<CookMenuAdapter.ViewHolder> {


    private Fragment fragment;
    private Context mContext;
    private LayoutInflater mInflater;

    private List<CookBookBean.DataBean> mDatas;

    public CookMenuAdapter(Fragment fragment, List<CookBookBean.DataBean> datas) {
        this.fragment = fragment;
        this.mContext = fragment.getActivity().getApplicationContext();
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cook_menu_item, null);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CookBookBean.DataBean cookMenu = mDatas.get(position);
        holder.title.setText(cookMenu.getTitle());

        List<String> tags = Arrays.asList(cookMenu.getTags().split(";"));
        holder.tagsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.tagsRecyclerview.addItemDecoration(new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL,
                R.drawable.common_divier_line_style));
        holder.tagsRecyclerview.setAdapter(new CookMenuTagAdapter(fragment,tags));

        holder.menuIntro.setText(cookMenu.getImtro());
        holder.ingredients.setText(cookMenu.getIngredients());
        holder.burden.setText(cookMenu.getBurden());
        holder.albumsViewPager.setAdapter(new CookMenuAlbumAdapter(fragment,cookMenu.getAlbums()));

        holder.stepsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.stepsRecyclerview.addItemDecoration(new RecycleViewDivider(mContext,
                LinearLayoutManager.VERTICAL,
                R.drawable.common_divier_line_style));
        holder.stepsRecyclerview.setAdapter(new CookMenuStepAdapter(fragment,cookMenu.getSteps()));

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

        TextView title;
        RecyclerView tagsRecyclerview;
        TextView menuIntro;
        TextView ingredients;//食材
        TextView burden;//配料
        ViewPager albumsViewPager;//作品影集
        RecyclerView stepsRecyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tagsRecyclerview = itemView.findViewById(R.id.tags_list);
            menuIntro = itemView.findViewById(R.id.intro_tv);
            ingredients = itemView.findViewById(R.id.ingredients_tv);
            burden = itemView.findViewById(R.id.burden_tv);
            albumsViewPager = itemView.findViewById(R.id.albums_viewpager);
            stepsRecyclerview = itemView.findViewById(R.id.steps_recycler);
        }
    }
}
