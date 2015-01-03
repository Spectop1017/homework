package com.example.spectop.spectop15010104.mainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.spectop.spectop15010104.R;
import com.example.spectop.spectop15010104.database.DataBaseHelper;
import com.example.spectop.spectop15010104.database.ProjectNameDao;

import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    private Button add_new_note;

    private ListView listView;
    private MyAdapter adapter;

    TextView textview,content;


    ProjectNameDao projectNameDao;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        listView = (ListView) findViewById(R.id.list_content);

        adapter = new MyAdapter(this);

        listView.setAdapter(adapter);

        add_new_note = (Button) findViewById(R.id.add_new_note);
        add_new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddNewNote.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        projectNameDao = new ProjectNameDao(this);

        textview = (TextView) findViewById(R.id.list_note_tittle);
        content = (TextView) findViewById(R.id.list_note_content);

        load();
    }

    private void load(){
        List<Map<String ,String >> list = projectNameDao.loadNote();

        Map<String ,String > map = list.get(0);

        textview.setText(map.get(DataBaseHelper.KEY_NOTE_TITTLE));
        content.setText(map.get(DataBaseHelper.KEY_NOTE_CONTENT));
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
