package com.example.daylight;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class customDialog extends Dialog {
    private Context context;
    private int layoutId;
    public customDialog(@NonNull Context context,int id) {
        super(context);
        layoutId=id;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        Button b = findViewById(R.id.randomBgm);

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Context c = v.getContext();

                MediaPlayer m = MediaPlayer.create(c , R.raw.bg1 );
                m.start();

                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp)
                    {
                        mp.stop();
                        mp.release();
                    }
                });


            }
        });
    }
}

