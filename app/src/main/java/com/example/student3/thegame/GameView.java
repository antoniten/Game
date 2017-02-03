package com.example.student3.thegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by student3 on 28.01.17.
 */
public class GameView extends SurfaceView implements Runnable {

    private boolean playing;
    private Thread gameThread = null;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Star> stars = new ArrayList<Star>();
    private Friend friend;


    public GameView(Context context,int screenX,int screenY){
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        friend = new Friend(context, screenX, screenY);
    }


    @Override
    public void run(){
        while (playing){
            update();
            draw();
        }
    }
    private void update(){
        for(Star s: stars){
            s.update(1);
        }
        friend.update(1);
    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);

            for(Star s:stars){
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }

            canvas.drawBitmap(friend.getBitmap(),friend.getX(),friend.getY(),paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    void pause(){
        playing = false;
        try{
            gameThread.join();
        }catch (InterruptedException e) {}
    }

   void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
