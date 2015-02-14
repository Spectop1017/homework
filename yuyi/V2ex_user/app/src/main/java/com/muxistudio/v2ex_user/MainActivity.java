package com.muxistudio.v2ex_user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    ImageButton search;
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
    search = (ImageButton) findViewById(R.id.main_button_search);
    editText = (EditText) findViewById(R.id.main_edit_search);

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataProcess dataProcess = new DataProcess();
            int select = 1;
            String infor,status;
            radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            if (!radioButton.getText().toString().equals("用户名查询")){
                select = 0;
            }
            infor = editText.getText().toString();
            status = dataProcess.getstatus(dataProcess.searchInformation(infor,select));

            if (status.equals("found")) {
                Intent intent = new Intent(MainActivity.this,UserPage.class);
                intent.putExtra("infor",infor);
                intent.putExtra("select",select);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(MainActivity.this,"没有这个用户",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    });

}

@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
