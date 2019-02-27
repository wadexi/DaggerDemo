package com.android.wadexi.basedemo.architecture.viewmodel.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.android.wadexi.basedemo.architecture.respository.RegionRespostory;
import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.architecture.respository.contentprovider.ResDataSource;
import com.android.wadexi.basedemo.beans.region.RegionArea;
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
    private Observer<List<RegionProvince>> listProvinceObserver;
    private Observer<List<RegionArea>> listAreaObserver;

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

    /**
     * 查询所有的省
     */
    public void queryProvince(){
        LiveData<List<RegionProvince>> listLiveData = regionRespostory.queryRegionProvince();
        if(Utils.checkNonNull(lifecycleOwner, listProvinceObserver)){
            listLiveData.observe(lifecycleOwner, listProvinceObserver);
        }
    }

    /**
     * 查询所有的省
     */
    public void queryAreas(){
        LiveData<List<RegionArea>> listLiveData = regionRespostory.queryRegionArea();
        if(Utils.checkNonNull(lifecycleOwner, listProvinceObserver)){
            listLiveData.observe(lifecycleOwner, listAreaObserver);
        }
    }


    public void setLifeCycleOwner(LifecycleOwner lifecycleOwner){
        this.lifecycleOwner = lifecycleOwner;
    }

    public void observeProvinceDatas(Observer<List<RegionProvince>> observer) {
        listProvinceObserver = observer;
    }

     public void observeAreaDatas(Observer<List<RegionArea>> observer) {
         listAreaObserver = observer;
    }


}
