package com.seven.segment.roadsign.blockdrop;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback
{
	private GameUpdateThread drawThread;
	
	private static final String TAG = GameSurface.class.getSimpleName();
	
	int boxX = 10;
	int boxY = 10;

	public GameSurface(Context context)
	{
		super(context);
		
		getHolder().addCallback(this);

		drawThread = new GameUpdateThread(getHolder(), this, context);

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
		//Check if the thread is paused. If it is, recreate it.
		if (drawThread.isPaused())
		{
			drawThread = new GameUpdateThread(getHolder(), this, this.getContext());
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
		drawThread.pauseThread();
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
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			boxX = (int) event.getX();
			boxY = (int) event.getY();
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{

	}
	
	public void update()
	{
	}
	
	public void render(Canvas c)
	{
		c.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r_bock), boxX, boxY, null);
	}
}