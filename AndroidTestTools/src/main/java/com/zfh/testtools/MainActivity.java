package com.zfh.testtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.zfh.adapter.MenuAdapter;
import com.zfh.fragment.BatteryFragment;
import com.zfh.fragment.HomeFragment;
import com.zfh.utils.StatusBarUtil;

import static android.R.id.toggle;
import static com.zfh.testtools.R.id.main_toolbar;

/**
 * Created by ZFH on 2016/10/21.
 * 此应用旨在练习安卓编程
 * 希望利用此应用达到如下目的
 * 1.仿谷歌应用商店界面
 * 2.记录电池信息并显示
 * 3.添加monkey自动测试
 * 4.自动安装app功能，如果能实现的话
 * 5.对屏幕方向及其它进行操作,蓝牙，wifi
 * 6.自动开关机测试
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private long firstTime = 0;
    public static DrawerLayout drawer_layout;   //整个应用只有一个DrawerLayout
    private ExpandableListView left_listview;
    private ExpandGroupLinstener groupLinstener;
    private ExpandChildLinstener childLinstener;
    private MenuAdapter menuAdapter;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      setColor();//SystemBarTint库实现状态栏颜色
        init();
        //StatusBarUtil工具类实现沉浸状态栏
        StatusBarUtil.setColorForDrawerLayout(this, drawer_layout, getResources().getColor(R.color.color_512da8),0);
    }

    //初始化资源
    public void init() {
        drawer_layout = (DrawerLayout) findViewById(R.id.activity_main);
        left_listview = (ExpandableListView) findViewById(R.id.left_listview);
        groupLinstener = new ExpandGroupLinstener();
        childLinstener = new ExpandChildLinstener();
        left_listview.setOnGroupClickListener(groupLinstener);
        left_listview.setOnChildClickListener(childLinstener);
        menuAdapter = new MenuAdapter(MainActivity.this);//此处之前出错，因为没有初始化类中的Context对象
        //侧栏绑定适配器
        left_listview.setAdapter(menuAdapter);
    }
    //ExpandableListView监听组列表下的子列表项
    class ExpandGroupLinstener implements ExpandableListView.OnGroupClickListener{
        //监听组列表
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            transaction = getSupportFragmentManager().beginTransaction();
            switch (groupPosition) {
                case 0: //主页
                    transaction.replace(R.id.tv_content, new HomeFragment());
                    break;
                case 1: //
                    break;
                case 2: //电池运行状况页面
                    Log.e(TAG, "groupPosition->" + groupPosition + "id->" + id);
                    transaction.replace(R.id.tv_content, new BatteryFragment());
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    break;
            }
//            transaction.addToBackStack(null);//将事务添加进返回栈
            transaction.commit();
            drawer_layout.closeDrawer(Gravity.LEFT);    //关闭左侧栏
            return false;//传入下级判断是否展开，所以返回false
        }
    }

    //ExpandableListView监听组列表下的子列表项
    class ExpandChildLinstener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            return true;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

/*
    //如果不使用此代码块，可以删除libs目录下的systembartint-1.0.4.jar文件
    //SystemBarTint库实现状态栏颜色
    public void setColor() {
        int color = getResources().getColor(R.color.color_000000);
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色，不设置默认为浅灰色
        tintManager.setTintColor(color);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
*/

}
