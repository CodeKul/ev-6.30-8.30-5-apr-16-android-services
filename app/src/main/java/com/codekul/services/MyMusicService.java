package com.codekul.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;

public class MyMusicService extends Service {

    private MyImplementer implementer = new MyImplementer();

    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i("@codekul","Bound to service");

        return implementer;
    }

    private class MyImplementer extends IComman.Stub{

        private MediaPlayer mediaPlayer;

        public MyImplementer(){
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource("/storage/sdcard1/my.mp3");
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void play() throws RemoteException {

            if(!mediaPlayer.isPlaying()){

                mediaPlayer.start();
            }
        }

        @Override
        public void pause() throws RemoteException {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
    }
}
