package com.example.a073105.daggerdemo.beans;

import android.graphics.Bitmap;

public class ContactData {

    private Bitmap headImgUrl;
    private String name;
    private String phoneNum;

    public ContactData(Bitmap headImgUrl, String name, String phoneNum) {
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public Bitmap getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(Bitmap headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "headImgUrl='" + headImgUrl + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
