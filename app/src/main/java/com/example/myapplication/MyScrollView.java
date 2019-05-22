package com.example.myapplication;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by lihongxin on 2019/1/18
 */
public class MyScrollView extends NestedScrollView {
    private ScrollChangedListener mListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollChangeListener(ScrollChangedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScrollChangedListener(l, t, oldl, oldt);
        }
    }

    public interface ScrollChangedListener {
        void onScrollChangedListener(int x, int y, int oldX, int oldY);
    }
}
