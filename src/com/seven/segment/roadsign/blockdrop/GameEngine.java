package com.seven.segment.roadsign.blockdrop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class GameEngine
{
	private double blockStart = 0.2;
	private double blocksEnd = 0.8;
	private int blocksXNum = 16; // 20 Blocks across the entire screen
	private int blocksYNum = 30;
	private GameSurface theView;

	private Rect rRect;
	private Rect bRect;
	private Rect gRect;
	private Rect yRect;
	private Rect tRect;
	private int imagesSet = 0;
	private int imageBlockW = 30;

	private int[][] blocksArr;

	private Bitmap blocksimage;

	public GameEngine(GameSurface gameSurface)
	{
		theView = gameSurface;

		// Create the bitmaps here, so that rendering is easier.
		blocksimage = BitmapFactory.decodeResource(theView.getResources(), R.drawable.blocks);

		// Create all the rectangles
		rRect = new Rect(0 * imageBlockW, imagesSet * imageBlockW, 1 * imageBlockW, (imagesSet + 1) * imageBlockW);
		bRect = new Rect(1 * imageBlockW, imagesSet * imageBlockW, 2 * imageBlockW, (imagesSet + 1) * imageBlockW);
		gRect = new Rect(2 * imageBlockW, imagesSet * imageBlockW, 3 * imageBlockW, (imagesSet + 1) * imageBlockW);
		yRect = new Rect(3 * imageBlockW, imagesSet * imageBlockW, 4 * imageBlockW, (imagesSet + 1) * imageBlockW);
		tRect = new Rect(4 * imageBlockW, imagesSet * imageBlockW, 5 * imageBlockW, (imagesSet + 1) * imageBlockW);

		// Create the data array
		blocksArr = new int[blocksXNum][blocksYNum];

		blocksArr[5][5] = 2;
		blocksArr[6][5] = 2;
		blocksArr[7][5] = 2;
		blocksArr[5][6] = 2;

	}

	public void update()
	{

	}

	public void render(Canvas c)
	{
		double blockWidth = c.getWidth() * (blocksEnd - blockStart) / blocksXNum;
		double xStartPos = c.getWidth() * blockStart;

		// Render all the blocks. Lets see how long it takes.
		// X direction
		for (int x = 0; x < blocksXNum; x++)
		{
			for (int y = 0; y < blocksYNum; y++)
			{
				Rect currBlock = null;
				switch (blocksArr[x][y])
				{
				case 0:
					currBlock = tRect;
					break;
				case 1:
					currBlock = rRect;
					break;
				case 2:
					currBlock = bRect;
					break;
				case 3:
					currBlock = gRect;
					break;
				case 4:
					currBlock = yRect;
					break;
				default:
					currBlock = rRect;
					break;
				}
				c.drawBitmap(blocksimage, currBlock, new Rect((int) (xStartPos + x * blockWidth), (int) (y * blockWidth), (int) (xStartPos + (x + 1) * blockWidth),
						(int) ((y + 1) * blockWidth)), null);

			}
		}
	}

	public void leftPressed()
	{
	}

	public void rightPressed()
	{
	}

	public void rotatePressed()
	{
	}

	public void downPressed()
	{

	}
}
