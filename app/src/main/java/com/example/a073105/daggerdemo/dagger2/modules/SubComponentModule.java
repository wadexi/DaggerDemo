package com.example.a073105.daggerdemo.dagger2.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.a073105.daggerdemo.activities.homepage.MainActivity;
import com.example.a073105.daggerdemo.activities.SettingActivity;
import com.example.a073105.daggerdemo.application.MyApplication;
import com.example.a073105.daggerdemo.dagger2.components.AppComponent;
//import com.example.a073105.daggerdemo.dagger2.components.MainActivityComponent;
import com.example.a073105.daggerdemo.dagger2.components.SettingActivityModule;
import com.example.a073105.daggerdemo.fragments.contacts.ConstantFragmentModel;
import com.example.a073105.daggerdemo.fragments.contacts.ContactsFragModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class SubComponentModule {


//    @Binds
//    @IntoMap
//    @ClassKey(MyApplication.class)
//    abstract AndroidInjector.Factory<?>
//        bindMyApplicationInjectorFactory(AppComponent.Builder builder);


//    @Binds
//    @IntoMap
//    @ClassKey(MainActivity.class)
//    abstract AndroidInjector.Factory<?>
//                                bindMainActivityInjectorFactory(MainActivityComponent.Builder builder);

    @ContributesAndroidInjector(modules = SettingActivityModule.class)
    abstract SettingActivity contributeSettingActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();


}
