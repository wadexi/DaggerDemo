package com.example.a073105.daggerdemo.dagger2.modules.activity;

import com.example.a073105.daggerdemo.architecture.ui.activities.SettingActivity;
import com.example.a073105.daggerdemo.architecture.ui.activities.MainActivity;
import com.example.a073105.daggerdemo.dagger2.annotation.ActivityScope;

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
