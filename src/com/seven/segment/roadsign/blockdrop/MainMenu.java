package com.seven.segment.roadsign.blockdrop;

//
//Handy for the emulator ^(?!.*(nativeGetEnabledTags)).*$ 
//

import android.os.Bundle;
import android.app.Activity;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurface(this));
    }
    
    @Override
    protected void onStart()
    {
   	 super.onStart();
    }
    
}
