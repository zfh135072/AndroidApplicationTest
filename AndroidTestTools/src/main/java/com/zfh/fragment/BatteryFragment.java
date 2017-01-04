package com.zfh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfh.testtools.R;
import com.zfh.utils.StatusBarUtil;

import static com.zfh.testtools.MainActivity.drawer_layout;

/**
 * Created by zfh on 16-12-4.
 */

public class BatteryFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        //设置好Activity状态栏颜色
        StatusBarUtil.setColorForDrawerLayout(getActivity(), drawer_layout, getResources().getColor(R.color.color_4bae4f),0);
        //本Fragment状态栏填充，颜色为Activity时状态栏颜色
        StatusBarUtil.resetFragmentView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battery_fragment,container,false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
