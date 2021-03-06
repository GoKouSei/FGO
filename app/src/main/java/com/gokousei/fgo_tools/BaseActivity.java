package com.gokousei.fgo_tools;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected static final String LOG_TAG = "com.gokousei.fgo_tools";
    protected static final String BASE_URL = "http://fgowiki.com";
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        translucentStatusNavigation();
    }

    //设置透明状态栏和导航栏
    private void translucentStatusNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //判断SDK版本
        //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {//判断触摸事件是否是点击
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹球或者实体按键会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//判断是否隐藏输入法键盘
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {//判断传递的view是否是EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);//获得view的窗口坐标
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            //通过点击的坐标和view的坐标对比判断点击的是否EditText
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //单例显示Toast方法
    protected void makeToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
