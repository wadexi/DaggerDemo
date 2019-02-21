package com.android.wadexi.basedemo.dagger2.modules.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.wadexi.basedemo.dagger2.annotation.AndroidViewModelKey;
import com.android.wadexi.basedemo.architecture.viewmodel.fragments.ConstantFragmentModel;
import com.android.wadexi.basedemo.dagger2.andriodviewmodelfactory.ContactsFragModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @AndroidViewModelKey(ConstantFragmentModel.class)//要使用ViewModelKey 因为其重写的value， Class<? extends ViewModel> value();
    abstract AndroidViewModel bindConstantViewModel(ConstantFragmentModel viewModel);


    @Binds
//    @IntoMap
//    @ClassKey(ContactsFragModelFactory.class)
    abstract ViewModelProvider.AndroidViewModelFactory bindViewModelFactory(ContactsFragModelFactory factory);
}
