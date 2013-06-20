package com.seven.segment.roadsign.blockdrop;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainPanel extends SurfaceView implements SurfaceHolder.Callback
{
	private MainThread drawThread;
	
	private static final String TAG = MainPanel.class.getSimpleName();

	public MainPanel(Context context)
	{
		super(context);
		
		getHolder().addCallback(this);

		drawThread = new MainThread(getHolder(), this, context);

		setFocusable(true);
	}

	@Override
	//Overridden from android.view.SurfaceHolder.Callback
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		
	}

	@Override
	//Overridden from android.view.SurfaceHolder.Callback
	public void surfaceCreated(SurfaceHolder holder)
	{
		//Check if the thread is started
		//TODO: Change this to use get/set
		if (drawThread.state==MainThread.PAUSED)
		{
			drawThread = new MainThread(getHolder(), this, this.getContext());
			drawThread.start();
		}
		else
		{
			drawThread.start();
		}
	}

	@Override
	//Overridden from android.view.SurfaceHolder.Callback
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		//Try to join the drawThread thread
		boolean retry = true;
		//TODO: Change this to use get/set methods
		drawThread.state=MainThread.PAUSED;
		while (retry)
		{
			try
			{
				drawThread.join();
				retry = false;
			}
			catch (InterruptedException e)
			{
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			if (event.getY() > getHeight() - 50)
			{
				((Activity) getContext()).finish();
			}
			else
			{
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{

	}
}