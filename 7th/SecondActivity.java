package com.muxistudio.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 2bab on 14-12-13.
 *
 */
public class SecondActivity extends Activity {
//第二个页面的对应程序，其中SecondActivity 是继承于Activity 的。

    private Button button;  //新建一个Button型变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//一定要有！！必须先执行父类的方法，再执行自己的

        setContentView(R.layout.activity_second);

        Intent from = getIntent();
        int fromInt = from.getIntExtra("String",0);//监听带入的数据

        if(fromInt!=0){

        }


        button = (Button) findViewById(R.id.second_button);//需要强制类型转化，否则得到的是View类型

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//这里准备将一些数据带出

                Bundle bundle = new Bundle();
                bundle.putInt("result",12345678);
                bundle.putString("String", "String");
                intent.putExtras(bundle);//bundle相当于把这些数据打包了

                intent.putExtra("name","amy");

                setResult(110,intent);//把结果带出
            }
        });

    }
}
