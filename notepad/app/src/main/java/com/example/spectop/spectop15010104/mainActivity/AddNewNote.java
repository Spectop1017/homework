package com.example.spectop.spectop15010104.mainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spectop.spectop15010104.R;
import com.example.spectop.spectop15010104.database.ProjectNameDao;


public class AddNewNote extends Activity {

    EditText tittle,content;
    ProjectNameDao projectNameDao;
    Button savebn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        init();
    }

    private void init() {
        projectNameDao = new ProjectNameDao(this);

        tittle = (EditText) findViewById(R.id.tittle_note);
        content = (EditText) findViewById(R.id.content_note_editor);
        savebn = (Button) findViewById(R.id.submite_note);

        savebn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }
    private void save(){
        //此处需要判断EditText是否为空，是否有非法数值
        projectNameDao.insertNote(tittle.getText().toString(),
                content.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_note, menu);
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
