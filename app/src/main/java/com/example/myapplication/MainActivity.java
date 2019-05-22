package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NestedScrollView scrollView;
    private int mActionBarHeight;


    private int topHeight;


    private Button button;
    private Button button2;
//-------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);
        toolbar = findViewById(R.id.toolbar);
        scrollView = findViewById(R.id.scrollView);
        Log.d("--------------",getStatusBarHeight(this)+"getActionBarHeight");
        Log.d("--------------",ViewUtils.dip2px(this,250)+"ViewUtils");
        topHeight=ViewUtils.dip2px(this,170);
//        topHeight = ViewUtils.dip2px(this, 250+getActionBarHeight(this)-getActionBarHeight(this)-getStatusBarHeight(this)-getStatusBarHeight(this)/2);
        ImmersionBar.with(this)
                .titleBar(toolbar, false)
                .transparentBar()
                .init();
        mActionBarHeight = getActionBarHeight(this)*2;
        Log.d("---------------------",button.getTop()+"");
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int scrollY, int i2, int oldScrollY) {
                if (scrollY > oldScrollY && scrollY <= mActionBarHeight) {
                    ImmersionBar.with(MainActivity.this)
                            .addViewSupportTransformColor(toolbar, R.color.colorPrimary)
                            .navigationBarColorTransform(R.color.colorAccent)
                            .barAlpha((float) scrollY / mActionBarHeight)
                            .init();
                } else if (scrollY==0){
                    ImmersionBar.with(MainActivity.this)
                            .barAlpha(0.f)
                            .titleBar(toolbar, false)
                            .transparentBar()
                            .init();
                }else if (scrollY < oldScrollY && scrollY <= mActionBarHeight){
                    ImmersionBar.with(MainActivity.this)
                            .addViewSupportTransformColor(toolbar, R.color.colorPrimary)
                            .navigationBarColorTransform(R.color.colorAccent)
                            .barAlpha((float) scrollY / mActionBarHeight)
                            .init();
                }else {
                    ImmersionBar.with(MainActivity.this)
                            .addViewSupportTransformColor(toolbar, R.color.colorPrimary)
                            .navigationBarColorTransform(R.color.colorAccent)
                            .barAlpha(1.0f)
                            .init();
                }
                Log.e("=============",button.getY()+"");

                if (scrollY>=topHeight){
                    button2.setVisibility(View.VISIBLE);
                }else {
                    button2.setVisibility(View.GONE);
                }
            }
        });
    }

    @TargetApi(14)
    private int getActionBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return result;
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
