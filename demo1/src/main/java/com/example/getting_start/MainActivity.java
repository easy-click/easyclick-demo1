package com.example.getting_start;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gibb.api.ApiStarter;
import com.run.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiStarter apiStarter = ApiStarter.getInstance(getApplicationContext());

        //打开悬浮窗
        ApiStarter.getInstance(getApplicationContext()).showFloatControlForUi("main.xml");

        apiStarter.setDefaultClass(Main.class.getName());

        View view = apiStarter.generateUiView("main.xml", true);
        
        setContentView(view);
    }
}
