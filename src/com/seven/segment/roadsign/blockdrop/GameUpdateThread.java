package com.seven.segment.roadsign.blockdrop;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;


/**
 * @author Kolijn Wolfaardt
 * @version 0.1
 */
public class GameUpdateThread extends Thread
{

	// flag to hold game state
	private SurfaceHolder mSurfaceHolder;
	private GameSurface mGamePanel;
	private GameEngine mGameEngine;

	private long delay;
	private long sleepTime = 70;

	private Paint blackPaint;

	// State of the Game
	boolean running = true;

	private static final String TAG = GameUpdateThread.class.getSimpleName();

	public GameUpdateThread(SurfaceHolder surfaceHolder, GameSurface gamePanel,GameEngine gameEngine)
	{
		super();
		this.mSurfaceHolder = surfaceHolder;
		this.mGamePanel = gamePanel;
		this.mGameEngine = gameEngine;

		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
	}
	
	/** 
	 * Disables updating of the game.
	 */
	public void pauseThread()
	{
		this.running = false;
	}
	
	/** 
	 * Enables updating of the game.
	 */
	public void runThread()
	{
		this.running = true;
	}
	
	/**
	 * 
	 * @return true is the game is paused
	 */
	public boolean isPaused()
	{
		return !running;
	}

	@Override
	/** 
	 * Runs the thread. Should only be called once, when the thread is created.
	 * 
	 * This function updates the game, draws it, and then sleeps for a given period.
	 */
	public void run()
	{
		Log.d("BLOCK","Thread Started Running");
		while (running == true)
		{
			// Measure the time before
			long beforeTime = System.nanoTime();

			//Update the Game
			mGameEngine.update();

			Canvas c = null;
			try
			{
				// lock canvas so nothing else can use it
				c = mSurfaceHolder.lockCanvas(null);
				//TODO: What happens if this returns null? How do we exit?
				if (c != null)
				{
					synchronized (mSurfaceHolder)
					{
						//Clear the screen
						c.drawRect(0, 0, c.getWidth(), c.getHeight(), blackPaint);
						
						//And render the Graphics
						mGameEngine.render(c);
					}
				}
			}
			catch (Exception e)
			{
				// TODO: Canvas Drawing Exception Handler
				Log.d(TAG,"Error in drawing" + e.toString());
			}
			finally
			{
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null)
				{
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}

			this.sleepTime = delay - ((System.nanoTime() - beforeTime) / 1000000L);

			try
			{
				if (sleepTime > 0)
				{
					Thread.sleep(sleepTime);
				}
			}
			catch (InterruptedException ex)
			{
				// TODO: Thread sleeping Exception Handler
				Log.d(TAG, "Exited from sleeping with error: " + ex);
			}
		}
	}

	/*
	 * Kudos to
	 * http://blorb.tumblr.com/post/236799414/simple-java-android-game-loop for
	 * writing an excellent post on the game loop
	 */
}
