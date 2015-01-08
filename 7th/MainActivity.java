package com.muxistudio.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button button;
    private Button buttonTwo;//新建两个Button型变量

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.main_button_one);
        buttonTwo = (Button) findViewById(R.id.main_button_two);

        /*
        String s = getResources().getString(R.string.hello_world);
        int marginLeft = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);*/

        button.setText("I'm a button");

        button.setBackgroundResource(R.drawable.ic_launcher);

        //第一种监听器，匿名内部类，最常见
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
            }
        });*/

        //第二种内部类
        //button.setOnClickListener(new OnButtonClickListener());
        //第二种的另外一种形式，可复用
//        OnButtonClickListener listener = new OnButtonClickListener();
//        button.setOnClickListener(listener);


        //第三种，activity 实现 OnCLickListener，一种比较简单的整理代码的形式
        button.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);


    }

    //第四种，直接用xml绑定到函数，该函数需要有一个 View 参数，不推荐使用这个方法
    /*public void function(View v){

    }*/

    //第三种监听
    @Override
    public void onClick(View v) {
        Log.e("onclick","start");
        int id = v.getId();
        switch (id){//以id区分对Button进行监听
            case R.id.main_button_one:
                button.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));//按钮事件：背景改变
                break;

            case R.id.main_button_two:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);//按钮事件：进入SecondActivity
                intent.putExtra("String",56);
                //startActivity(intent);

                startActivityForResult(intent,100);//吧第一个页面的数据带入第二个页面
                break;

            default:
                break;//这里面的三个break千万不要忘记
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==110){
            //do something
            String name = data.getStringExtra("name");

            Bundle bundle = data.getExtras();
            int result = bundle.getInt("result",-1);
        }
    }

    /*class OnButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }*/

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
