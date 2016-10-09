package edu.purdue.wang2542.hackny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jingzhouwang on 16/10/8.
 */



public class Profile extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Button con = (Button)findViewById(R.id.button3);
        con.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edit1 = (EditText)findViewById(R.id.editText); // name
                EditText edit2 = (EditText)findViewById(R.id.editText2); // male
                EditText edit3 = (EditText)findViewById(R.id.editText3); // Phone Number
            //    TextView tv1 = (TextView)findViewById(R.id.textView4);
                MainActivity.name = edit1.getText().toString();
                MainActivity.male = edit2.getText().toString();
                MainActivity.phone = edit3.getText().toString();
                System.out.println(MainActivity.name);
                System.out.println(MainActivity.male);
                System.out.println(MainActivity.phone);
            }
        });

    }

//    String name = String.valueOf(findViewById(R.id.editText));

    public void Continue(View view) {
        Intent contin = new Intent(this, photo1.class);
        startActivity(contin);
    }
}
