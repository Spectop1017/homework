package com.example.root.spectop_notepad_v1.module.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.spectop_notepad_v1.R;
import com.example.root.spectop_notepad_v1.database.ProjectNameDao;


public class MainActivity extends ActionBarActivity {
    ProjectNameDao projectNameDao;

    EditText title , note ;
    Button save , load ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        projectNameDao = new ProjectNameDao(this);

        title = (EditText) findViewById(R.id.main_content_title);
        note = (EditText) findViewById(R.id.main_content_note);
        save = (Button) findViewById(R.id.main_save);
        load = (Button) findViewById(R.id.main_load);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private void save(){
        projectNameDao.insert(title.getText().toString(),note.getText().toString());
    }

    private void load(){
        Intent intent = new Intent(MainActivity.this,ListActivity.class);
        startActivity(intent);
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
