package com.nested.Qi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        Thread thread=new Thread(){
            @Override
            public void run(){
                try {
                    DashboardFragment.topimageUrls.clear();
                    TopQi topQi=new TopQi(WelcomActivity.this);
                    topQi.execute(new String("Text"));

                    DashboardFragment.imageUrls.clear();
                    ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(WelcomActivity.this);
                    viewPagerAdapter.execute(new String("Text"));




                    sleep(1000);

                }catch (Exception e){
                    e.printStackTrace();

                }finally {

                    Intent mainIntent=new Intent(WelcomActivity.this,MainActivity.class);
                    startActivity(mainIntent);


                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
