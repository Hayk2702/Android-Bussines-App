package com.nested.Qi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{
  //  TextView text;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }

    boolean connected = false;
    boolean boollang=true;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //  text=(TextView)findViewById(R.id.textView6);

        Paper.init(this);
        String language=Paper.book().read("language");
        if(language==null){
            Paper.book().write("language","en");
            boollang=false;
            updateView((String)Paper.book().read("language"));

        }


      /*  int highScore=getIntent().getIntExtra("lang",0);
        if(highScore==0){
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            int highScore1 = sharedPref.getInt("KEY", 2);
            if(highScore1<2){
                setAppLocale("hy");
            }
            else {
                setAppLocale("en");
            }
        }else if(highScore<2){
            setAppLocale("hy");
        }
        else {
            setAppLocale("en");
        }
*/

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        if(connected) {
            NotificationsFragment.exampleList.clear();
            featchData fd = new featchData(MainActivity.this);
            fd.execute(new String("Text"));



        }
        else {
        }



        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());


    }

    private void updateView(String language) {
        Context context=LocaleHelper.setLoacal(this,language);
        Resources resources=context.getResources();
       // text.setText(resources.getString(R.string.qi));
            startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.language_en){
            Paper.book().write("language","en");
            updateView((String)Paper.book().read("language"));

        }else  if(item.getItemId()==R.id.language_hy){
            Paper.book().write("language","hy");
            updateView((String)Paper.book().read("language"));

        }


        return true;
    }



    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment=null;

        switch (menuItem.getItemId()){

            case R.id.navigation_home:
                fragment=new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                fragment=new DashboardFragment();
                break;

            case R.id.navigation_notifications:
                if(connected) {
                    fragment = new NotificationsFragment();
                }else {
                    fragment=new internetCheck();

                }
                break;
        }

        return loadFragment(fragment);
    }





}
