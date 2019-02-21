package com.android.wadexi.basedemo.architecture.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.ConstantFragmentModel;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.HomeFragmentModel;
import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.beans.ResponBean;

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
        model = ViewModelProviders.of(this,viewModelFactory).get(HomeFragmentModel.class);
        model.getMutableLiveData().observe(this, new Observer<CookBookBean>() {
            @Override
            public void onChanged(@Nullable CookBookBean cookBookBeanResponBean) {
                Log.d(TAG, "onChanged: " + cookBookBeanResponBean.toString());
            }
        });

        model.getCookMenu("土豆丝",2);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView content = rootView.findViewById(R.id.content_text);
        content.setText(this.content);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
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
