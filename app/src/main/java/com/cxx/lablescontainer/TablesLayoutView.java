package com.cxx.lablescontainer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by cuixiaoxiao on 2020-07-02 23:29
 * @description
 */
public class TablesLayoutView extends ViewGroup {
    /*
     * 保存每一行的行高
     */
    private List<Integer> listHeigh = new ArrayList<>();
    /**
     * 保存所有的行的view
     */
    private List<List<View>> listView = new ArrayList<>();
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
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取当前view的测量模式
        int ownWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int ownHeighMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取父控件的尺寸
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeigh = MeasureSpec.getSize(heightMeasureSpec);
        //当前行的宽高(当前layout)
        int measureWidth = 0,measureHeigh = 0;
        if(MeasureSpec.EXACTLY == ownWidthMode && MeasureSpec.EXACTLY == ownHeighMode){
            //如果宽高都为固定尺寸，则当前layout的宽高为固定尺寸
            measureWidth = parentWidth;
            measureHeigh = parentHeigh;
        }else{
            //当前子view的实际宽高
            int iChildWidth,iChildHeigh;
            //临时存放的当前行的宽高值
            int iCurWidth = 0,iCurHeigh = 0;
            //如果都为最大模式
            //获取layout中子view的个数
            int chileCount = getChildCount();
            //存放当前行的view
            List<View> curHorView = new ArrayList<>();
            for(int i = 0; i < chileCount;i++){
                View child = getChildAt(i);
                //子view测量自己
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
                //得到margin测量类
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
                iChildWidth = marginLayoutParams.leftMargin + child.getMeasuredWidth() + marginLayoutParams.rightMargin;
                iChildHeigh = marginLayoutParams.topMargin + child.getMeasuredHeight() + marginLayoutParams.bottomMargin;
                if(iCurWidth + iChildWidth > parentWidth){
                    //之前累加的宽度+当前子view的宽度>父控件的宽度时，要换行
                    listView.add(curHorView);
                    //保留行高后重新设置当前行高
                    measureHeigh += iCurHeigh;
                    listHeigh.add(iCurHeigh);
                    iCurHeigh = iChildHeigh;
                    //控件的宽度取当前宽度和历史宽度的最大值
                    measureWidth = Math.max(measureWidth,iCurWidth);
                    //记录当前的行宽
                    iCurWidth = iChildWidth;
                    curHorView = new ArrayList<>();
                    curHorView.add(child);
                }else{
                    iCurWidth += iChildWidth;
                    iCurHeigh = Math.max(iChildHeigh,iChildHeigh);
                    curHorView.add(child);
                }
            }
            setMeasuredDimension(measureWidth,measureHeigh);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //当前view的位置值
        int left,top,right,bottom;
        //累计的左边距，累加的高度
        int curLeft = 0,curTop = 0;
        for(int index=0;index < listView.size();index++){
            List<View> list = listView.get(index);
            for(int j = 0;j < list.size();j++){
                //找到当前view的位置信息
                View view = list.get(j);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                left = curLeft + marginLayoutParams.leftMargin;
                top = curTop + marginLayoutParams.topMargin;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
                curLeft += view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
            curTop += listHeigh.get(index);
            curLeft = 0;
        }
        listHeigh.clear();
        listView.clear();
    }
}
