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
		setContentView(new GameSurface(this));

		leftButton = new Button(this);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

}
