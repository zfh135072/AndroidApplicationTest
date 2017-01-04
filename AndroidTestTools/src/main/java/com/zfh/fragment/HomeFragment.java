package com.zfh.fragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zfh.testtools.MainActivity;
import com.zfh.testtools.R;
import com.zfh.utils.StatusBarUtil;

import java.util.Random;

import static android.R.id.toggle;
import static com.zfh.testtools.MainActivity.drawer_layout;
import static com.zfh.utils.StatusBarUtil.resetFragmentView;

/**
 * A simple {@link Fragment} subclass.
 * 主页fragment
 */
public class HomeFragment extends Fragment {
    private final String TAG = "HomeFragment";
    private Toolbar home_toolbar;   //Toolbar
    private ActionBarDrawerToggle home_toggle;  //Toolbar开关
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRegister();
    }
    private void initView() {
        //设置好Activity状态栏颜色
        StatusBarUtil.setColorForDrawerLayout(getActivity(), drawer_layout, getResources().getColor(R.color.color_512da8),0);
        //本Fragment状态栏填充，颜色为Activity时状态栏颜色
        StatusBarUtil.resetFragmentView(this);
        //组件初始化
        home_toolbar = (Toolbar) getActivity().findViewById(R.id.home_toolbar);
        //设置toolbar左侧导航键图标，默认id为android.R.id.home
        home_toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        home_toggle = new ActionBarDrawerToggle(getActivity(), drawer_layout,home_toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //home_toggle绑定drawer_layout，控制打开与关闭
        drawer_layout.addDrawerListener(home_toggle);
        home_toggle.syncState();
    }
    private void initRegister() {
        //广播或服务等注册
    }
}
