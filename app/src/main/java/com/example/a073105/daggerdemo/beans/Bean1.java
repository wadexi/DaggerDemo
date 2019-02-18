package com.example.a073105.daggerdemo.beans;

import javax.inject.Inject;

public class Bean1 {

    private String id;

    @Inject
    public Bean1(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "id='" + id + '\'' +
                '}';
    }
}
