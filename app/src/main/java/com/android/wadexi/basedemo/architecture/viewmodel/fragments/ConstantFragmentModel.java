package com.android.wadexi.basedemo.architecture.viewmodel.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.architecture.respository.contentprovider.ResDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConstantFragmentModel extends AndroidViewModel {

    private static final String TAG = "ConstantFragmentModel";
    private MutableLiveData<List<ContactData>> mutableLiveData = new MutableLiveData<>();

    private Application application;



    ResDataSource dataSource;

    @Inject
    public ConstantFragmentModel(@NonNull Application application,  ResDataSource dataSource) {
        super(application);
        this.application = application;
        this.dataSource = dataSource;
    }

    //    @Inject
//    public ConstantFragmentModel(Application application,ResDataSource dataSource) {
//        this.application = application;
//        this.dataSource = dataSource;
//    }

    //    public ConstantFragmentModel(@NonNull Application application) {
//        super(application);
//        this.application = application;
//    }


    public MutableLiveData<List<ContactData>> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * 获取联系人数据
     */
    public void getContactDatas() {
        List<ContactData> contactDatas = dataSource.getContactDatas(application);
        mutableLiveData.setValue(contactDatas);
    }



}
