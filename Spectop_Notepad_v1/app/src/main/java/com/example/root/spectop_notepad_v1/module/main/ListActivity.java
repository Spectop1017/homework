package com.example.root.spectop_notepad_v1.module.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.spectop_notepad_v1.R;
import com.example.root.spectop_notepad_v1.database.DataBaseHelper;
import com.example.root.spectop_notepad_v1.database.ProjectNameDao;

import java.util.List;
import java.util.Map;


public class ListActivity extends ActionBarActivity {
    int Index_note;

    TextView title_list,note_list;
    Button home,back,next;

    ProjectNameDao projectNameDao;

    List<Map<String ,String >> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = projectNameDao.loadNote();

        title_list = (TextView) findViewById(R.id.list_title);
        note_list = (TextView) findViewById(R.id.list_note);
        home = (Button) findViewById(R.id.list_home);
        back = (Button) findViewById(R.id.list_back);
        next = (Button) findViewById(R.id.list_next);

        Index_note = 0;
        addnote(Index_note);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Index_note--;
                addnote(Index_note);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Index_note++;
                addnote(Index_note);
            }
        });

    }

    private void addnote(int i) {
        if (list != null) {
            Map<String, String> map = list.get(i);
            title_list.setText(map.get(DataBaseHelper.KEY_NOTE_TITLE));
            note_list.setText(map.get(DataBaseHelper.KEY_NOTE_CONTENT));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
