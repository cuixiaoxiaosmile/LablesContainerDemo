package com.cxx.lablescontainer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by cuixiaoxiao on 2020-07-05 18:04
 * @description
 */
public class TablesLayoutAgainView extends ViewGroup {
    public TablesLayoutAgainView(Context context) {
        super(context);
    }

    public TablesLayoutAgainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TablesLayoutAgainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取自定义view的父控件的宽高(即TablesLayoutAgainView外包裹的LinearLayout的宽高)
        int totleHeigh = MeasureSpec.getSize(heightMeasureSpec);
        int totleWidth = MeasureSpec.getSize(widthMeasureSpec);
        //获取自定义view的父控件的模式(即TablesLayoutAgainView外包裹的LinearLayout的模式)
        int totleHeighMode = MeasureSpec.getMode(heightMeasureSpec);
        int totleWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取父控件内容展示区域的宽高
        int totleContainWidth = totleWidth - getPaddingLeft() - getPaddingRight();
        int totleContainHeigh = totleHeigh - getPaddingTop() - getPaddingBottom();
        //遍历子view
        int childCount = getChildCount();
        for(int i = 0;i < childCount;i++){
            View child = getChildAt(i);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(totleContainWidth,totleWidthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : totleWidth);
            int childHeighMeasureSpec = MeasureSpec.makeMeasureSpec(totleWidth,totleHeighMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : totleHeigh);
            child.measure(childWidthMeasureSpec,childHeighMeasureSpec);
        }
        setMeasuredDimension(totleWidth,resolveSize(totleHeigh,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for(int i = 0;i < count;i++){
            View view = getChildAt(i);
            view.layout(l,t,r,b);
        }
    }
}
