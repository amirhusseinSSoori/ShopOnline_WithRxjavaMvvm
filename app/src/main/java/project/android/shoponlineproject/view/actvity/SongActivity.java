package project.android.shoponlineproject.view.actvity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import project.android.shoponlineproject.R;

public class SongActivity extends AppCompatActivity {

    SeekBar seekBar;

    CircleImageView imageView;

    static MediaPlayer mediaPlayer;
    TextView txt_endtime,txt_starttime,txt_titel;
    Button next,play,back;

    ImageView layout;



    Timer timer;
    Bundle bundle;


    int REQUEST_EXTERNAL_ID=1000;
    int number=3;

    private NotificationManagerCompat mNotificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        init();

        mNotificationManagerCompat= NotificationManagerCompat.from(this);



        Intent intent=getIntent();
        bundle=intent.getExtras();
        mediaPlayer=new MediaPlayer();

       Glide.with(getApplicationContext()).load(bundle.getString("image")).into(imageView);
        Glide.with(getApplicationContext()).load(bundle.getString("image")).into(layout);

        txt_titel.setText(bundle.getString("name"));

        setUpMusicPlayer();





    }



    void setUpMusicPlayer(){

        try {
            mediaPlayer.setDataSource(bundle.getString("color"));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mediaPlayer) {

                    playmusic();
                    nextBack();
                    seekBar();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void playmusic(){

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();


                    play.setBackgroundResource(R.drawable.play_button);
                }else {
                    mediaPlayer.start();

                    play.setBackgroundResource(R.drawable.ic_pause);
                }
            }
        });

        mediaPlayer.seekTo(0);

        txt_endtime.setText(set_Time(mediaPlayer.getDuration()));

    }
    void seekBar(){
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);

                    txt_starttime.setText(set_Time(mediaPlayer.getCurrentPosition()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        timer=new Timer();
        timer.schedule(new Timertask(),0,1000);
    }
    public String set_Time(long progress){
        int sec=(int)progress/1000;
        int min=sec/60;
        sec%=60;
        return String.format(Locale.ENGLISH,"%02d",min) + ":" + String.format(Locale.ENGLISH,"%02d",sec);
    }
    void nextBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);

            }
        });
    }
    void init(){
        layout=(ImageView) findViewById(R.id.layoutRelative);
        imageView=(CircleImageView)findViewById(R.id.circul_artist);
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        txt_endtime=(TextView)findViewById(R.id.endtime);
        txt_starttime=(TextView)findViewById(R.id.starttime);


        back=(Button)findViewById(R.id.btn_back);
        txt_titel=(TextView)findViewById(R.id.name_titel);

        play=(Button)findViewById(R.id.btn_play);

        next=(Button)findViewById(R.id.btn_next);


    }

    public class Timertask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    txt_starttime.setText(set_Time(mediaPlayer.getCurrentPosition()));
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer !=null){
            try{
                timer.cancel();

                mediaPlayer.release();

            }catch (Exception e){

            }

        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(1);
        if(mediaPlayer !=null){
            try{
                timer.cancel();

                mediaPlayer.release();

            }catch (Exception e){

            }

        }

        finish();
        super.onBackPressed();
    }





    }
//    public void showNotification(){
//        RemoteViews callapsedView=new RemoteViews(getPackageName(),R.layout.notification_callapsed);
//        RemoteViews expandedView=new RemoteViews(getPackageName(),R.layout.notification_expanded);
//
//        Intent intclick=new Intent(this,MusicRiciver.class);
//        PendingIntent clickPending=PendingIntent.getBroadcast(this,0,intclick,0);
//
//        callapsedView.setTextViewText(R.id.text_view_one,"KingMusic");
//
//
//        expandedView.setOnClickPendingIntent(R.id.play_noti,clickPending);
//        expandedView.setTextViewText(R.id.name_song_artist,bundle.getString("artist"));
//
//
//
//        Notification notification=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID_1)
//                .setSmallIcon(R.drawable.heart)
//                .setCustomContentView(callapsedView)
//                .setCustomBigContentView(expandedView)
//                .setOngoing(true)
////                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                .build();
//
//        mNotificationManagerCompat.notify(1,notification);
//
//
//    }





