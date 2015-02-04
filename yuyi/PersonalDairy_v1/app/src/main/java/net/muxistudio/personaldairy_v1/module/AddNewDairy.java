package net.muxistudio.personaldairy_v1.module;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.muxistudio.personaldairy_v1.R;
import net.muxistudio.personaldairy_v1.database.MyDairyDao;


public class AddNewDairy extends ActionBarActivity {
    MyDairyDao myDairyDao;

    EditText title, content;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dairy);

        myDairyDao = new MyDairyDao(this);
        title = (EditText) findViewById(R.id.edit_add_title);
        content = (EditText) findViewById(R.id.edit_add_content);

        save = (Button) findViewById(R.id.button_add_finish);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDairyDao.insert(title.getText().toString(),content.getText().toString());
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_dairy, menu);
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
