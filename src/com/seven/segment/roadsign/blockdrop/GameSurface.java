package com.seven.segment.roadsign.blockdrop;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback
{
	private GameUpdateThread drawThread;

	private static final String TAG = GameSurface.class.getSimpleName();

	private GameEngine ge;

	public GameSurface(Context context)
	{
		super(context);
		//startThread();

	}

	public GameSurface(Context context, AttributeSet attrs)
	{
		super(context,attrs);
		
		/*TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GameSurface);

		Log.i("test", a.getString(R.styleable.GameSurface_android_padding));

		// Don't forget this
		a.recycle();*/
		
		//startThread();
	}

	@Override
	// Overridden from android.view.SurfaceHolder.Callback
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}
	
	public void startThread()
	{
		// Add Callback for SurfaceHolder.callback
		getHolder().addCallback(this);

		// Create the gameEngine
		ge = new GameEngine(this);

		// Create the game Loop Thread
		drawThread = new GameUpdateThread(getHolder(), this, ge);
		
		//drawThread.run();

		setFocusable(true);
	}

	@Override
	// Overridden from android.view.SurfaceHolder.Callback
	public void surfaceCreated(SurfaceHolder holder)
	{
		// Check if the thread is paused. If it is, recreate it.
		if (drawThread.isPaused())
		{
			drawThread = new GameUpdateThread(getHolder(), this, ge);
			drawThread.start();
		}
		else
		{
			drawThread.start();
		}
	}

	@Override
	// Overridden from android.view.SurfaceHolder.Callback
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// Try to join the drawThread thread
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
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{

	}
}