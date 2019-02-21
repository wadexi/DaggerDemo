package com.android.wadexi.basedemo.dagger2.modules.activity;

import com.android.wadexi.basedemo.architecture.ui.activities.SettingActivity;
import com.android.wadexi.basedemo.architecture.ui.activities.MainActivity;
import com.android.wadexi.basedemo.dagger2.annotation.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class SubComponentModule {



//    @Binds
//    @IntoMap
//    @ClassKey(MainActivity.class)
//    abstract AndroidInjector.Factory<?>
//                                bindMainActivityInjectorFactory(MainActivityComponent.Builder builder);

    @ActivityScope
    @ContributesAndroidInjector(modules = SettingActivityModule.class)
    abstract SettingActivity contributeSettingActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();


}
