package com.example.a073105.daggerdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.a073105.daggerdemo.R;
import com.example.a073105.daggerdemo.fragments.ConstantFragment;
import com.example.a073105.daggerdemo.fragments.FindFragment;
import com.example.a073105.daggerdemo.fragments.HomeFragment;
import com.example.a073105.daggerdemo.fragments.MeFragment;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                    HomeFragment.OnFragmentInteractionListener,
                    ConstantFragment.OnFragmentInteractionListener,
                    FindFragment.OnFragmentInteractionListener,
                    MeFragment.OnFragmentInteractionListener ,
                    HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    @Named("mainactivity_string")
    String title;

//    FrameLayout container;
    FragmentManager supportFragmentManager;
    final HashMap<Class<? extends Fragment>,Fragment> fragmentHashMap = new HashMap<>(4);

    DrawerLayout drawerLayout;

    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingActionBar();
        settingHomeFragment();
        settingBottonMenu();



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


        /*
          已经存在该碎片
         */
        if(fragmentHashMap.containsKey(fragmentClazz)){
            Fragment fragment = fragmentHashMap.get(fragmentClazz);
            switchFragmentNow(fragment);

        }else {//不存在该碎片
            try {
                Fragment fragment =  fragmentClazz.newInstance();
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
}
