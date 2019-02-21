package com.example.a073105.daggerdemo.dagger2.modules.activity;

import com.example.a073105.daggerdemo.activities.SettingActivity;
import com.example.a073105.daggerdemo.activities.homepage.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class SubComponentModule {



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
