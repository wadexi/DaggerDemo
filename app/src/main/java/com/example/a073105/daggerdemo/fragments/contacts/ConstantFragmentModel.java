package com.example.a073105.daggerdemo.fragments.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.a073105.daggerdemo.R;
import com.example.a073105.daggerdemo.beans.ContactData;
import com.example.a073105.daggerdemo.respository.contentprovider.ResDataSource;

import java.io.InputStream;
import java.util.ArrayList;
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
