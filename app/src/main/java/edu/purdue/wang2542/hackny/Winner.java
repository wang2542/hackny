package edu.purdue.wang2542.hackny;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by jingzhouwang on 16/4/21.
 */
public class Winner extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winervideo);

        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());

        System.out.println("android.resource://" + getPackageName() + "/");
        videoView.setVideoURI(Uri.parse("file://" + Environment.getExternalStorageDirectory().getParent() + "/0/Download/winner.mp4"));
        //videoView.setVideoURI(Uri.parse("https://drive.google.com/a/vt.edu/file/d/0B85j2CCAgiy5NHFBMi1OeUhHc3M/view?usp=sharing"));
        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.winner));

        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();





    }


    public void ContinueGame(View view) {
        Intent GoHead = new Intent(this, MainActivity.class);
        startActivity(GoHead);
    }
}
