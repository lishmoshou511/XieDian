package com.xiedian.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

/**
 * Created by lishuang on 2014/7/4.
 */
public class ChartView extends View {
    private int x=100;
    private int y=100;
    private int r;
    private int step=1;
    private Paint paint;
    private Context context;


    public ChartView(Context context) {

        super(context);
        this.context=context;
        paint = new Paint();


        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;

        float height = size.y;
        float imageWidth=640.0f;
        float imageHeight=960.0f;

        float rate=(imageWidth/x)*y/imageHeight;

        System.out.println("宽度："+width+" 高度："+height);

        x=(int)(width*788.0f/1000.0f);
        y=(int)(height*rate*372.0f/1000.0f);


    }
    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.YELLOW);


        canvas.drawCircle(x,y,r,paint);

    }

    private int rInPx=7;
    public void update(){

        if(rInPx==10){
            step=-1;
        }

        if(rInPx==7){
            step=1;
        }

        rInPx+=step;

        r=dip2px(context,rInPx);

        //强制重绘
        this.postInvalidate();

    }

    public int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}

