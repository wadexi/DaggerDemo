package com.android.wadexi.basedemo.architecture.viewmodel.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.wadexi.basedemo.architecture.respository.RegionRespostory;
import com.android.wadexi.basedemo.architecture.ui.fragments.ConstantFragment;
import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.architecture.respository.contentprovider.ResDataSource;
import com.android.wadexi.basedemo.beans.region.RegionProvince;
import com.android.wadexi.basedemo.utils.Utils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConstantFragmentModel extends AndroidViewModel {

    private static final String TAG = "ConstantFragmentModel";
    private final RegionRespostory regionRespostory;
    private MutableLiveData<List<ContactData>> contactLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> provinceLiveData = new MutableLiveData<>();
    private MutableLiveData<List<RegionProvince>> provinceDatas = new MutableLiveData<>();

    private Application application;



    ResDataSource dataSource;
    private LifecycleOwner lifecycleOwner;
    private Observer<List<RegionProvince>> listObserver;

    @Inject
    public ConstantFragmentModel(@NonNull Application application, ResDataSource dataSource, RegionRespostory respostory) {
        super(application);
        this.application = application;
        this.dataSource = dataSource;
        this.regionRespostory = respostory;
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


    public MutableLiveData<List<ContactData>> getContactLiveData() {
        return contactLiveData;
    }


    public MutableLiveData<Boolean> getProvinceLiveData() {
        return provinceLiveData;
    }

    public MutableLiveData<List<RegionProvince>> getProvinceDatas() {
        return provinceDatas;
    }

    /**
     * 获取联系人数据
     */
    public void getContactDatas() {
        List<ContactData> contactDatas = dataSource.getContactDatas(application);
        contactLiveData.setValue(contactDatas);
    }


    public void addProvince(){
        regionRespostory.addRegionProvince();
        provinceLiveData.setValue(true);
    }

    public void queryProvince(){
        LiveData<List<RegionProvince>> listLiveData = regionRespostory.queryRegionProvince();
        if(Utils.checkNonNull(lifecycleOwner,listObserver)){
            listLiveData.observe(lifecycleOwner,listObserver);
        }
    }


    public void setLifeCycleOwner(LifecycleOwner lifecycleOwner){
        this.lifecycleOwner = lifecycleOwner;
    }

    public void observeProvinceDatas(Observer<List<RegionProvince>> observer) {
        listObserver = observer;
    }
}
