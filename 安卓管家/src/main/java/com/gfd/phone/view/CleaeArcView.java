package com.gfd.phone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义控件的相关方法：
 * 1.onMeasure():测量，决定自己大小
 * 2.onLayout() ；布局：决定自己的位置
 * 3.onDraw() :绘制 ：决定自己的样子
 */
public class CleaeArcView extends View {

    private RectF rectF;
    /** 扫描的角度：决定圆弧的弧长*/
    private float sweepAngle = 90;
    private Paint mPaint;
    /** 状态：0:表示后退 1：前进 */
    private int state = 0;
    private boolean isRuning;
    private int[] back = {5,8,10,12,15,18,12,6};
    private int[] go = {8,10,14,18,12,10};
    private int indexBack;

    public CleaeArcView(Context context) {
        this(context, null);
    }

    /**
     * 有属性的时候
     */
    public CleaeArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 有风格的时候
     */
    public CleaeArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化操作
        //抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(20);
    }

    /**
     * 测量
     * @param widthMeasureSpec  ：既有测量的大小又有测量的规则
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽和高的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0,0,width,height);
        //设置测量的尺寸
        setMeasuredDimension(width,height);

    }

    /**
     * 测量
     * @param canvas ：画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆弧:1.决定位置矩形 2。起始角度（顺时针是+）（-90：表示从正上方画）
        //3.true :表示扇形 false:圆弧 4.画笔
        canvas.drawArc(rectF,-90, sweepAngle,true,mPaint);
    }

    /**
     * 开始开始的角度
     * @param toAngle
     */
    public void setStartAngle(final int toAngle){
        this.sweepAngle = toAngle;
        postInvalidate();
    }
    /**
     * 先从当前的状态回到0的位置，然后再从0的位置转到指定的位置
     * @param toAngle
     */
    public void setSweepAngle(final int toAngle){
        if(isRuning)return;
        final Timer timer = new Timer(false);
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                switch (state){
                    case 0://后退
                        //先归0
                        isRuning = true;
                        sweepAngle -= back[indexBack++];
                        if(indexBack >= back.length){
                            indexBack = back.length - 1;
                        }
                        if(sweepAngle <=0 ){
                            sweepAngle = 0;
                            state = 1;
                        }
                        postInvalidate();//在子线程中刷新UI
                        //开始到指定的位置
                        break;
                    case 1://前进
                        sweepAngle += 10;
                        if(sweepAngle >= toAngle){
                            sweepAngle = toAngle;
                            //结束
                            timer.cancel();
                            state = 0;
                            isRuning = false;
                            indexBack = 0;
                        }
                        postInvalidate();
                        break;
                }

            }
        };
        timer.schedule(timerTask,45,45);

    }
    public  void setColor(String color){
        mPaint.setColor(Color.parseColor(color));//设置画笔的颜色
        invalidate();
    }
}
