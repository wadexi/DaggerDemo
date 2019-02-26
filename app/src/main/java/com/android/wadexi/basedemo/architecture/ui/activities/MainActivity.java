package com.android.wadexi.basedemo.architecture.ui.activities;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.wadexi.basedemo.R;
import com.android.wadexi.basedemo.architecture.respository.db.AppDB;
import com.android.wadexi.basedemo.architecture.ui.fragments.ConstantFragment;
import com.android.wadexi.basedemo.architecture.ui.fragments.FindFragment;
import com.android.wadexi.basedemo.architecture.ui.fragments.HomeFragment;
import com.android.wadexi.basedemo.architecture.ui.fragments.MeFragment;
import com.android.wadexi.basedemo.architecture.viewmodel.activity.HomeViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                    HomeFragment.OnFragmentInteractionListener,
                    ConstantFragment.OnFragmentInteractionListener,
                    FindFragment.OnFragmentInteractionListener,
                    MeFragment.OnFragmentInteractionListener ,
                    HasSupportFragmentInjector /*,
                    EasyPermissions.PermissionCallbacks,
                    EasyPermissions.RationaleCallbacks*/{

    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0x01;

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    @Named("mainactivity_string")
    String title;

    @Inject
    AppDB appDB;

//    FrameLayout container;
    FragmentManager supportFragmentManager;
    final HashMap<Class<? extends Fragment>,Fragment> fragmentHashMap = new HashMap<>(4);

    DrawerLayout drawerLayout;

    RadioGroup radioGroup;
    private final int RC_READ_WRITE_EXTERNAL_STORAGE = 0x101;
    private String[] perms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        settingActionBar();
        settingHomeFragment();
        settingBottonMenu();

        if(requestPermission()){
            initRegionData();
        }

    }

    private void initRegionData() {

        appDB.executeSqlFile(getApplicationContext(), "region/region_province", this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d(TAG, "onChanged: 初始化省数据完成");
            }
        });
    }

    private void initViewModel() {

        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getUserImgUrl().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, "onChanged: urlImgUrl:" + s);
            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    /**
     * Constant 碎片传送过来的数据
     * @param data 碎片传送过来的数据
     */
    @Override
    public void dataFromConstant(String data) {

    }

    /**
     * Find 碎片传送过来的数据
     * @param data 碎片传送过来的数据
     */
    @Override
    public void dataFromFind(String data) {

    }

    /**
     * Home 碎片传送过来的数据
     * @param data 碎片传送过来的数据
     */
    @Override
    public void dataFromHome(String data) {

    }

    /**
     * Me 碎片传送过来的数据
     * @param data  碎片传送过来的数据
     */
    @Override
    public void dataFromMe(String data) {

    }

    /**
     * 设置底部菜单按钮事件
     */
    private void settingBottonMenu() {

        radioGroup = findViewById(R.id.rg_bottom_menu);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_homepage:
                        switchFragment(HomeFragment.class);
                        break;
                    case R.id.rb_address_book:
                        switchFragment(ConstantFragment.class);
                        break;
                    case R.id.rb_find:
                        switchFragment(FindFragment.class);
                        break;
                    case R.id.rb_me:
                        switchFragment(MeFragment.class);
                        break;
                }
            }
        });
    }

    /**
     * 切换碎片
     */
    private void switchFragment(Class<? extends Fragment> fragmentClazz){


        Fragment fragment = fragmentHashMap.get(fragmentClazz);
        switchFragmentNow(fragment);
        /*
          不存在该碎片
         */
        if(fragment == null){
            try {
                fragment =  fragmentClazz.newInstance();
                fragmentHashMap.put(fragmentClazz,fragment);
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container,fragment,fragmentClazz.getSimpleName());
                fragmentTransaction.commitNow();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }






    }

    /**
     * 切换碎片
     * @param fragment 要显示的碎片
     */
    private void switchFragmentNow(Fragment fragment) {

        for (Fragment next : fragmentHashMap.values()) {
            if (next == fragment) {
                if (next.isHidden()) {
                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                    fragmentTransaction.show(next);
                    fragmentTransaction.commitNow();
                }
            } else {
                if (!next.isHidden()) {
                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                    fragmentTransaction.hide(next);
                    fragmentTransaction.commitNow();
                }
            }


        }


    }

    private void settingHomeFragment() {
        supportFragmentManager = getSupportFragmentManager();
//        container = findViewById(R.id.container);
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        HomeFragment homeFragment = HomeFragment.newInstance();
        fragmentHashMap.put(HomeFragment.class,homeFragment);
        fragmentTransaction.add(R.id.container,homeFragment,HomeFragment.class.getSimpleName());
        fragmentTransaction.commitNow();
    }

    /**
     * title
     */
    private void settingActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.mipmap.ic_apps_black_24dp);
        supportActionBar.setHomeButtonEnabled(true);


        drawerLayout = findViewById(R.id.drawer_layout);

//        drawerLayout.openDrawer(GravityCompat.START); 都可以


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,SettingActivity.class));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                if(drawerLayout.isDrawerVisible(Gravity.START)){
                    drawerLayout.closeDrawer(Gravity.START);
                }else {
                    drawerLayout.openDrawer(Gravity.START);
                }

                break;
            case R.id.setting_menu:
                startActivity(new Intent(this,SettingActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private boolean requestPermission() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission_group.STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission_group.STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE

                        },
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initRegionData();
                }
                break;


        }
    }

    //    @AfterPermissionGranted(RC_READ_WRITE_EXTERNAL_STORAGE)
//    private void methodRequiresTwoPermission() {
//        perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//        }
//        if (EasyPermissions.hasPermissions(this.getApplicationContext(), perms)) {
//
//            Toast.makeText(this, "已经有读写权限", Toast.LENGTH_SHORT).show();
//            // Already have permission, do the thing
//            // ...
//        } else {
//            // Do not have permissions, request them now
//
//            EasyPermissions.requestPermissions(
//                    new PermissionRequest.Builder(this, RC_READ_WRITE_EXTERNAL_STORAGE, perms)
//                            .setRationale("请求读写内存卡的权限")
//                            .setPositiveButtonText("确定")
//                            .setNegativeButtonText("取消")
//                            .setTheme(R.style.Theme_AppCompat_Light_Dialog_Alert)
//                            .build());
//        }
//    }
//
//    @Override
//    public void onRationaleAccepted(int requestCode) {
//            EasyPermissions.requestPermissions(this, "请求读写权限",
//                    RC_READ_WRITE_EXTERNAL_STORAGE, perms);
//    }
//
//    @Override
//    public void onRationaleDenied(int requestCode) {
//        Toast.makeText(this, "请求权限被拒绝", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//
//    @Override
//    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
//            // Do something after user returned from app settings screen, like showing a Toast.
//            Toast.makeText(this, "获取到读取权限", Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this).build().show();
//        }
//    }
}
