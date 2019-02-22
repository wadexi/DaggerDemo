package com.android.wadexi.basedemo.architecture.ui.adapters.cookmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.glide.setting.GlideApp;

import java.util.List;

public class CookMenuAlbumAdapter extends PagerAdapter {

    List<String> mDatas;
    Fragment fragment;
    Context mContext;

    public CookMenuAlbumAdapter(Fragment fragment,List<String> mDatas) {
        this.mDatas = mDatas;
        this.fragment = fragment;
        this.mContext = fragment.getActivity().getApplicationContext();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(
                new ConstraintLayout.LayoutParams(ConstraintLayout.MarginLayoutParams.MATCH_PARENT,
                                                  ConstraintLayout.MarginLayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String url = mDatas.get(position);
        GlideApp.with(fragment).load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
