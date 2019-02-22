package com.android.wadexi.basedemo.architecture.ui.fragments;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.architecture.ui.adapters.ContactAdapter;
import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.ConstantFragmentModel;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConstantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConstantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstantFragment extends Fragment {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0x0001;
    Context applicationContext;
    RecyclerView recyclerView;
    List<ContactData> mDatas = new ArrayList<>();
    ContactAdapter contactAdapter;



    @Inject
    public ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    ConstantFragmentModel model;

    private static final String TAG = "ConstantFragment";

    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the CONTACT_KEY column
    private static final int CONTACT_KEY_INDEX = 1;

    private OnFragmentInteractionListener mListener;

    public ConstantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConstantFragment.
     */
    public static ConstantFragment newInstance() {
        ConstantFragment fragment = new ConstantFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        applicationContext = getActivity().getApplicationContext();
        initView();

        model = ViewModelProviders.of(this,viewModelFactory).get(ConstantFragmentModel.class);
        model.getMutableLiveData().observe(this, new Observer<List<ContactData>>() {
            @Override
            public void onChanged(List<ContactData> contactData) {
                mDatas.clear();
                mDatas.addAll(contactData);
            }
        });


        if(requestPermission()){
            initData();
        }

    }

    private boolean requestPermission() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {
            return true;
        }
        return false;
    }

    private void initData() {

        model.getContactDatas();


    }

    /**
     * 初始化控件
     */
    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(applicationContext,LinearLayout.VERTICAL,false));
        contactAdapter = new ContactAdapter(applicationContext, mDatas);
        recyclerView.setAdapter(contactAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_constant, container, false);
        recyclerView = rootView.findViewById(R.id.contacts_recyclerview);
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
        void dataFromConstant(String uri);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    initData();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
