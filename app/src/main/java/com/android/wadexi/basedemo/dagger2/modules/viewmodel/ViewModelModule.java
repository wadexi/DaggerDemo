package com.android.wadexi.basedemo.dagger2.modules.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.wadexi.basedemo.architecture.viewmodel.fragments.HomeFragmentModel;
import com.android.wadexi.basedemo.dagger2.annotation.AndroidViewModelKey;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.ConstantFragmentModel;
import com.android.wadexi.basedemo.dagger2.andriodviewmodelfactory.MyAndroidViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
//    @IntoMap
//    @ClassKey(MyAndroidViewModelFactory.class)
    abstract ViewModelProvider.AndroidViewModelFactory bindViewModelFactory(MyAndroidViewModelFactory factory);

    @Binds
    @IntoMap
    @AndroidViewModelKey(ConstantFragmentModel.class)//要使用ViewModelKey 因为其重写的value， Class<? extends ViewModel> value();
    abstract AndroidViewModel bindConstantViewModel(ConstantFragmentModel viewModel);


    @Binds
    @IntoMap
    @AndroidViewModelKey(HomeFragmentModel.class)//要使用ViewModelKey 因为其重写的value， Class<? extends ViewModel> value();
    abstract AndroidViewModel bindHomeViewModel(HomeFragmentModel viewModel);



}
