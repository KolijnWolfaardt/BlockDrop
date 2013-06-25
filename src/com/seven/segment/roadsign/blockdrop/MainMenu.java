package com.seven.segment.roadsign.blockdrop;

//
//Handy for the emulator ^(?!.*(nativeGetEnabledTags)).*$ 
//

import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;

public class MainMenu extends Activity
{
	Button leftButton;
	Button rightButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_menu);
		
		//First, let's find the gamesurface
		//GameSurface gs = (GameSurface) findViewById(R.id.gameSurface1);
		
		/*
		//First, let's get a handle on the Layout
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout1);
		layout.setPadding(0, 0, 0, 0);
		
		//Create the GameSurface, set properties, and add
		GameSurface gs = new GameSurface(this);
		gs.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,200));
		gs.setPadding(0, 0, 0, 0);
		layout.addView(gs);
		
		Button leftButton = new Button(this);
		leftButton.setText("Left");
		leftButton.setPadding(0, 0, 0, 0);
		leftButton.setLayoutParams(new ViewGroup.LayoutParams(300,50));
		layout.addView(leftButton);*/
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

}
