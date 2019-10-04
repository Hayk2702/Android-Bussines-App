package com.nested.Qi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MoreInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Fresco.initialize(this);

        String imageUrl=getIntent().getStringExtra("imageUrl");
        String im=imageUrl.substring(28,29);
        String imurl="https://mysmartech.ru/Qi/im"+im+".png";
        String text1=getIntent().getStringExtra("title");
        String text2=getIntent().getStringExtra("description");
       final String phone1=getIntent().getStringExtra("phone");
        final double lat=getIntent().getDoubleExtra("x",0);
       final double lng=getIntent().getDoubleExtra("y",0);
       String text=getIntent().getStringExtra("alltext");

        TextView test=findViewById(R.id.textView5);
        test.setText(text1);

        TextView test1=findViewById(R.id.textView4);
        test1.setText(text2);

        TextView textView=findViewById(R.id.textView3);
        textView.setText(text);
        Log.d("TEXT",text);

        ImageView imageMore=findViewById(R.id.imageMore);
        SimpleDraweeView posterImage = new SimpleDraweeView(getApplicationContext());
        Uri imgURI = Uri.parse(imurl);
        posterImage.setImageURI(imgURI);
       imageMore.setImageDrawable(posterImage.getDrawable());
        // Picasso.get().load(imurl).into(imageMore);
        Button mapButton=findViewById(R.id.map_button_2);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=lat;
                double y=lng;


                Intent intent=new Intent(v.getContext(),MapsActivity.class);
                intent.putExtra("Latitude",x);
                intent.putExtra("Longitude",y);

                v.getContext().startActivity(intent);
            }
        });

        Button callButton=findViewById(R.id.call_button_2);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phone1;
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phone));
                if(ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity)v.getContext(),new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else {
                    v.getContext().startActivity(intent);
                }


            }
        });


    }

}
