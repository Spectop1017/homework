package com.muxistudio.v2ex_user;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class UserPage extends ActionBarActivity {
    TextView username,id,tagline,twitter,github,bio;
    ImageView picture;
    Button follow,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        final DataProcess dataProcess = new DataProcess();
        String infor;
        int select;

        username = (TextView) findViewById(R.id.userpage_username);
        id = (TextView) findViewById(R.id.userpage_id_V);
        tagline = (TextView) findViewById(R.id.userpage_tagline);
        twitter = (TextView) findViewById(R.id.userpage_twitter_V);
        github = (TextView) findViewById(R.id.userpage_github_V);
        bio = (TextView) findViewById(R.id.userpage_bio_V);
        picture = (ImageView) findViewById(R.id.userpage_picture);
        follow = (Button) findViewById(R.id.userpage_follow);
        back = (Button) findViewById(R.id.userpage_back);

        Bundle extras =getIntent().getExtras();
        infor = extras.getString("infor");
        select = extras.getInt("select");

        String st;
        Bitmap pic;
        List<Map<String ,String >> list ;
        list = dataProcess.searchInformation(infor,select);

        st = dataProcess.getid(list);
        id.setText(st);

        st = dataProcess.getusername(list);
        username.setText(st);

        st = dataProcess.gettagline(list);
        tagline.setText(st);

        st = dataProcess.gettwitter(list);
        twitter.setText(st);

        st = dataProcess.getgithub(list);
        github.setText(st);

        st = dataProcess.getbio(list);
        bio.setText(st);

        pic = dataProcess.regetpic(dataProcess.getpicture(list));
        picture.setImageBitmap(pic);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_page, menu);
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
