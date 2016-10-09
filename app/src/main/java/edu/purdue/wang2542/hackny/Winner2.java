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
 * Created by jingzhouwang on 16/4/22.
 */
public class Winner2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);

        VideoView videoView = (VideoView)findViewById(R.id.winner2);
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());

        videoView.setVideoURI(Uri.parse("file://" + Environment.getExternalStorageDirectory().getParent() + "/0/Download/winner2.mp4"));
        //videoView.setVideoURI(Uri.parse("https://drive.google.com/a/vt.edu/file/d/0B85j2CCAgiy5T0xyN0FqWkRRaTg/view?usp=sharing"));

        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();





    }


    public void ContinueGame(View view) {
        Intent GoHead = new Intent(this, MainActivity.class);
        startActivity(GoHead);
    }
}
