package edu.purdue.wang2542.hackny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jingzhouwang on 16/4/20.
 */
public class Colour extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colour);
    }

    public void ColourWhite(View view) {
        MainActivity.colour = 1;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }

    public void ColourBlack(View view) {
        System.out.println(view.getId());
        MainActivity.colour = 2;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }

    public void ColourRed(View view) {
        MainActivity.colour = 3;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }

    public void ColourCyan(View view) {
        MainActivity.colour = 4;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }

    public void ColourGreen(View view) {
        MainActivity.colour = 5;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }

    public void ColourMagenta(View view) {
        MainActivity.colour = 6;
        Intent colour = new Intent(this, Menu.class);
        startActivity(colour);
    }


}
