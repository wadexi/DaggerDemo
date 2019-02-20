package com.example.a073105.daggerdemo.fragments.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ContactsFragModelFactory extends ViewModelProvider.AndroidViewModelFactory {


    private final Map<Class<? extends AndroidViewModel>,Provider<AndroidViewModel>> creators;



    @Inject
    public ContactsFragModelFactory(Application application,Map<Class<? extends AndroidViewModel>, Provider<AndroidViewModel>> creators) {
        super(application);
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends AndroidViewModel>, Provider<AndroidViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
