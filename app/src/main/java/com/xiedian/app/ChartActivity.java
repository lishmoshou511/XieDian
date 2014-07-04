package com.xiedian.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.xiedian.view.ChartView;

import java.util.Date;


public class ChartActivity extends Activity {

    private ChartView chartView;

    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_chart);

        chartView=new ChartView(this);




        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.chart_layout);
        relativeLayout.addView(chartView);

        thread =new Thread(new Runnable() {
            @Override
            public void run() {
                while (chartView!=null){
                    chartView.update();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        if (!thread.isAlive()) {
            thread.start();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private long timeStamp=0;
    //点击屏幕事件
	@Override
	public boolean onTouchEvent(android.view.MotionEvent event){

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            timeStamp=System.currentTimeMillis();
        }




		if(event.getAction()== MotionEvent.ACTION_UP){

            long currentTime=System.currentTimeMillis();
            if(currentTime-timeStamp > 1000){
                //视为长按

            }else{
                //视为点击

                Intent intent=new Intent(this,RemindActivity.class);
                startActivity(intent);
            }
        }



		return super.onTouchEvent(event);
	}



}
