package android.xxxeonm.testvideostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView vvStreaming;
    ImageButton btnPlayPause;

    ProgressDialog progressDialog; // ProgressBar 적용,

    String videoUrl; /*intent.getExtras().getString("validUrl");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Log.d("MainActivity***", intent.getStringExtra("validUrl"));
        videoUrl = intent.getStringExtra("validUrl");

        vvStreaming = (VideoView)findViewById(R.id.vv_main);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_main_play_pause);
        btnPlayPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

        try {
            if (!vvStreaming.isPlaying()) {

                Uri uri = Uri.parse(videoUrl);
                vvStreaming.setVideoURI(uri);
                vvStreaming.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                    }
                });
            } else {
                // pause 되는데, progressdialog 계속;;
                vvStreaming.pause();
                btnPlayPause.setImageResource(R.drawable.ic_play);
            }
        } catch (Exception e) {

        }

        vvStreaming.requestFocus();
        vvStreaming.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                mp.setLooping(true);
                vvStreaming.start();

                btnPlayPause.setImageResource(R.drawable.ic_pause);
            }
        });
    }
}
