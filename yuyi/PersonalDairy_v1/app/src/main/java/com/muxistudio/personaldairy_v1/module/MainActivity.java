package com.muxistudio.personaldairy_v1.module;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.muxistudio.personaldairy_v1.R;


public class MainActivity extends ActionBarActivity {
    private DairyAdapter adapter;
    int ADD_NEW_REQUEST_CODE;

    ImageButton addnewdairy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addnewdairy = (ImageButton) findViewById(R.id.button_add_new_dairy);

        ListView listView = (ListView) findViewById(R.id.list_dairy_list);
        adapter = new DairyAdapter(this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        //Adapter

        addnewdairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewDairy.class);
                startActivityForResult(intent, ADD_NEW_REQUEST_CODE);
            }
        });
        //添加新日记 -->跳转到新页面


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_REQUEST_CODE) {
            Log.i("onActivityResult", "on");
            adapter.refreshList();
        }
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
