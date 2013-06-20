package com.seven.segment.roadsign.blockdrop;

import android.os.Bundle;
import android.app.Activity;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MainPanel(this));
    }
    
    @Override
    protected void onStart()
    {
   	 super.onStart();
    }
    
}
