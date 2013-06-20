package com.seven.segment.roadsign.blockdrop;

import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{

	// flag to hold game state
	private SurfaceHolder surfaceHolder;
	private MainPanel gamePanel;
	private boolean runNow = false;

	private static final String TAG = MainThread.class.getSimpleName();

	public MainThread(SurfaceHolder surfaceHolder, MainPanel gamePanel)
	{
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}


	@Override
	public void run()
	{
		Log.d(TAG, "Starting game loop");
		/*while (running)
		{
			tickCount++;
			// update game state
			// render state to the screen
		}
		Log.d(TAG, "Game loop executed " + tickCount + " times");*/

	}
}
