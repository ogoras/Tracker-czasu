package com.example.trackerczasu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends Activity {
<<<<<<< Updated upstream
    private final int SPLASH_DISPLEY_LEHGHT = 10; //2000 ms
=======
    private final int SPLASH_DISPLEY_LEHGHT = 100; //2000 ms
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splashscreen.this, MainActivity.class);
                Splashscreen.this.startActivity(mainIntent);
                Splashscreen.this.finish();
            }
        }, SPLASH_DISPLEY_LEHGHT);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
