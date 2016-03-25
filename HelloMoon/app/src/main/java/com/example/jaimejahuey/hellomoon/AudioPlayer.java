package com.example.jaimejahuey.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jaimejahuey on 3/25/16.
 */
public class AudioPlayer
{
    private MediaPlayer mPlayer;

    public void stop()
    {
        if(mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c) {
        //Prevents the creationg of multiple instances of the MediaPlayer
        //So if the user where to press the play button twice.
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        mPlayer.start();
    }
}
