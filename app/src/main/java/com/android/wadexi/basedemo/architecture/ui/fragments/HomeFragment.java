package com.android.wadexi.basedemo.architecture.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.architecture.ui.adapters.cookmenu.CookMenuAdapter;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.HomeFragmentModel;
import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.beans.ResponBean;
import com.android.wadexi.basedemo.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    @Inject
    @Named("HomeFragmentString")
    String content;

    @Inject
    public ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    private OnFragmentInteractionListener mListener;
    private HomeFragmentModel model;


    CookMenuAdapter cookMenuAdapter;



    private List<CookBookBean.DataBean> mDatas = new ArrayList<>();
    private RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cookMenuAdapter = new CookMenuAdapter(this, mDatas);
        recyclerView.setAdapter(cookMenuAdapter);
        model = ViewModelProviders.of(this,viewModelFactory).get(HomeFragmentModel.class);
        model.getMutableLiveData().observe(this, new Observer<ResponBean<CookBookBean>>() {
            @Override
            public void onChanged(@Nullable ResponBean<CookBookBean> cookBookBeanResponBean) {
                CookBookBean cookBookBean = cookBookBeanResponBean.getT();
                Log.d(TAG, "onChanged: " + cookBookBean.toString());
                mDatas.clear();
                mDatas.addAll(cookBookBean.getData());
                cookMenuAdapter.notifyDataSetChanged();
            }
        });

        model.getCookMenu("土豆丝",2);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.home_fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity().getApplicationContext(),
                                        LinearLayoutManager.VERTICAL,
                                        R.drawable.common_divier_line_style));
        return rootView;
    }


    @Override
    public void onAttach(Context context) {

        int temp = 0;
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void dataFromHome(String uri);
    }
}
