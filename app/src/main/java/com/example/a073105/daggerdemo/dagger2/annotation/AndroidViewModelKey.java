package com.example.a073105.daggerdemo.dagger2.annotation;

import android.arch.lifecycle.AndroidViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface AndroidViewModelKey {
    Class<? extends AndroidViewModel> value();
}
