package com.android.wadexi.basedemo.architecture.respository.contentprovider;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.beans.ContactData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResDataSource {

    private static final String TAG = "ResDataSource";

    @Inject
    public ResDataSource() {
    }



    /**
     * 获取联系人数据
     */
    public List<ContactData>  getContactDatas(Application application) {
        List<ContactData> mDatas = new ArrayList<>();
        ContentResolver resolver = application.getContentResolver();
        Cursor descCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Phone.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                        ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,
                null,
                "desc");

        if (descCursor != null) {
            while (descCursor.moveToNext()) {

                int contactId = descCursor.getInt(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
//                vnd.android.cursor.item/phone_v2
//                String mimeType = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.MIMETYPE));
//                content://com.android.contacts/contacts/2/photo
//                String photothumbnail = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));
//                String photoUri = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                //content://com.android.contacts/display_photo/1
//                String path = Utils.PhotoUriUtils.getPath(applicationContext, Uri.parse(photoUri));
                int photoid = descCursor.getInt(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));

                //得到联系人头像Bitamp
                Bitmap contactPhoto = null;

                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                if(photoid > 0 ) {
                    Uri uri =ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,contactId);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                }else {
                    contactPhoto = BitmapFactory.decodeResource(application.getResources(), R.mipmap.ic_launcher_round);
                }

                String name = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String photoNum = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.d(TAG, "onCreate:   name:" + name + "  photoNum:" + photoNum);


                mDatas.add(new ContactData(contactPhoto,name,photoNum));
            }


        }
        return mDatas;
    }



}
