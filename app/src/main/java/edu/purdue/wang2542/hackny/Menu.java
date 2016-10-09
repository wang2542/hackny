package edu.purdue.wang2542.hackny;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by jingzhouwang on 16/10/8.
 */
public class Menu extends Activity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.menu);
    }


    public void Start(View view) {
        Intent start = new Intent(this, Profile.class);
        startActivity(start);

    }
}
