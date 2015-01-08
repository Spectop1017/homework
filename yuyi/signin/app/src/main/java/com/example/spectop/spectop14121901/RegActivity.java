package com.example.spectop.spectop14121901;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegActivity extends Activity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        back = (Button) findViewById(R.id.reg_13);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText namer = (EditText) findViewById(R.id.reg_02);
                EditText passr = (EditText) findViewById(R.id.reg_04);
                Bundle data = new Bundle();
                data.putString("namer",namer.toString());
                data.putString("passr",passr.toString());
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reg, menu);
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

