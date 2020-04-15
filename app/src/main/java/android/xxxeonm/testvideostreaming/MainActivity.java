package android.xxxeonm.testvideostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    ImageButton btnPlayPause;

    ProgressDialog progressDialog; // ProgressBar 적용,

    String videoUrl = "https://r3---sn-npoe7ney.googlevideo.com/videoplayback?expire=1586952440&ei=l6SWXsTvO5PBhwaShKLoDQ&ip=104.144.141.191&id=o-ALiD83Ydzs0AQ4qfmpwd6mpNknpkueWE4PZf3OuwVmST&itag=22&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ratebypass=yes&dur=1096.771&lmt=1586916286550388&fvip=6&c=WEB&txp=5535432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=AJpPlLswRQIhAJ1x8v7ktREkPbOt60xaZDAbiWOATISbU3nxDGAku92EAiBuZRgNq4FC41zBQjTIzm3pXiD6Qh-g4L-nfynqjg71hQ%3D%3D&redirect_counter=1&cm2rm=sn-ab5eee7s&req_id=d1c909325e62a3ee&cms_redirect=yes&mh=1m&mip=211.195.238.27&mm=34&mn=sn-npoe7ney&ms=ltu&mt=1586929204&mv=u&mvi=2&pl=23&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=ALrAebAwRQIgSof8oLK6VmflSlej8lHaDyA1PS1y_RQ033XPxEZrbKICIQCSnWG7-fzLv_lSpBBsYzEotgkyJtpFxQwWPcfLfNsq0w%3D%3D";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.vv_main);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_main_play_pause);
        btnPlayPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

        try {
            if (!videoView.isPlaying()) {

                Uri uri = Uri.parse(videoUrl);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                    }
                });
            } else {
                // pause 되는데, progressdialog 계속;;
                videoView.pause();
                btnPlayPause.setImageResource(R.drawable.ic_play);
            }
        } catch (Exception e) {

        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                mp.setLooping(true);
                videoView.start();

                btnPlayPause.setImageResource(R.drawable.ic_pause);
            }
        });
    }
}
