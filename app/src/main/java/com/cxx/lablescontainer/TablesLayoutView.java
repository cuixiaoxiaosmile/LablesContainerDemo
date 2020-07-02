package com.cxx.lablescontainer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author Created by cuixiaoxiao on 2020-07-02 23:29
 * @description
 */
public class TablesLayoutView extends ViewGroup {
    public TablesLayoutView(Context context) {
        super(context);
    }

    public TablesLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TablesLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取当前view的测量模式
        int ownWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int ownHeighMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取父控件的尺寸
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeigh = MeasureSpec.getSize(heightMeasureSpec);
        //view的宽高(当前layout)
        int ownWidth,ownHeigh;
        if(MeasureSpec.EXACTLY == ownWidthMode && MeasureSpec.EXACTLY == ownHeighMode){
            //如果宽高都为固定尺寸，则当前layout的宽高为固定尺寸
            ownWidth = parentWidth;
            ownHeigh = parentHeigh;
        }else if(MeasureSpec.AT_MOST == ownWidthMode && MeasureSpec.AT_MOST == ownHeighMode){
            //如果都为最大模式
            //获取layout中子view的个数
            int chileCount = getChildCount();
        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
