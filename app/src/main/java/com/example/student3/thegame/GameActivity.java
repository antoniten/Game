package com.example.student3.thegame;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by student3 on 28.01.17.
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        gameView = new GameView(this,size.x,size.y);
        setContentView(gameView);
    }

    @Override
    protected void  onPause(){
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
