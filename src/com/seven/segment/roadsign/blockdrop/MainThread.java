package com.seven.segment.roadsign.blockdrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{

	// flag to hold game state
	private SurfaceHolder mSurfaceHolder;
	private MainPanel mGamePanel;
	private Context mContext;

	private long delay;
	private long sleepTime = 70;

	private Paint mLinePaint;
	private Paint blackPaint;

	// State of the Game
	int state = 1;
	public final static int RUNNING = 1;
	public final static int PAUSED = 2;

	private static final String TAG = MainThread.class.getSimpleName();

	public MainThread(SurfaceHolder surfaceHolder, MainPanel gamePanel, Context context)
	{
		super();
		this.mSurfaceHolder = surfaceHolder;
		this.mGamePanel = gamePanel;
		this.mContext = context;

		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		// mLinePaint.setAntiAlias(true);
	}

	@Override
	public void run()
	{
		Log.d(TAG, "Starting game loop");
		while (state == RUNNING)
		{
			// Measure the time before
			long beforeTime = System.nanoTime();

			// update();

			// draw();
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
						// clear the screen
						c.drawRect(0, 0, c.getWidth(), c.getHeight(), blackPaint);
						// draw();
					}
				}
			}
			catch (Exception e)
			{
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
				// TODO: Exception Handler
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
