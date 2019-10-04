package com.nested.Qi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import io.paperdb.Paper;

public class DashboardFragment extends Fragment {
    AnimationDrawable rocketAnimation;

   static ArrayList<String> imageUrls = new ArrayList<String>();
    static ArrayList<String> topimageUrls = new ArrayList<String>();


    @SuppressLint({"ResourceType", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // HOME HOME HOME HOME HOME HOME

        final View view=inflater.inflate(R.layout.fragment_dashboard, null);
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.scroll_linear) ;

        Fresco.initialize(getContext());


        for(int i=0;i<imageUrls.size();i++){
            final Button btn=new Button(view.getContext());
            LinearLayout. LayoutParams params = new LinearLayout.LayoutParams(
                    300,300
            );
            params.setMargins(0, 0, 20,0);
            btn.setLayoutParams(params);
            btn.setId(i);
            ImageView imageView=new ImageView(view.getContext());

            Uri uri = Uri.parse(imageUrls.get(i));
            SimpleDraweeView draweeView =new SimpleDraweeView(view.getContext());
            draweeView.setImageURI(uri);
            btn.setBackground(draweeView.getDrawable());

           /* Picasso.get().load(imageUrls.get(i)).into(new Target(){


                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    btn.setBackground(new BitmapDrawable(view.getContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }


                @Override
                public void onPrepareLoad(final Drawable placeHolderDrawable) {
                }
            });*/
            String str1=String.valueOf(imageUrls.get(i));
            int m=str1.length();
            int n=m-32;
            String str=str1.substring(28,28+n);

            final int finalI = Integer.valueOf(str)-1;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExampleAdapter mAdapter;
                    mAdapter=new ExampleAdapter(NotificationsFragment.exampleList);
                    String imageUrl=NotificationsFragment.exampleList.get(finalI).getmImageUrl();
                    String title=NotificationsFragment.exampleList.get(finalI).getmText1();
                    String description=NotificationsFragment.exampleList.get(finalI).getmText2();
                    double x=NotificationsFragment.exampleList.get(finalI).getmX();
                    double y=NotificationsFragment.exampleList.get(finalI).getmY();
                    String phone=NotificationsFragment.exampleList.get(finalI).getmPhoneNumber();
                    String alltext=NotificationsFragment.exampleList.get(finalI).getmAllText();
                    Intent intent=new Intent(getContext(),MoreInfoActivity.class);
                    intent.putExtra("imageUrl",imageUrl);
                    intent.putExtra("title",title);
                    intent.putExtra("description",description);
                    intent.putExtra("x",x);
                    intent.putExtra("y",y);
                    intent.putExtra("phone",phone);
                    intent.putExtra("alltext",alltext);
                    getContext().startActivity(intent);


                }
            });

            linearLayout.addView(btn);

        }


        LinearLayout linearLayout1=(LinearLayout)view.findViewById(R.id.top_linear) ;



        for(int i=0;i<topimageUrls.size();i++){
            final Button btn1=new Button(view.getContext());
            LinearLayout. LayoutParams params = new LinearLayout.LayoutParams(
                    334,501
            );
            params.setMargins(0, 0, 20,0);
            btn1.setLayoutParams(params);
            btn1.setId(i);
            ImageView imageView=new ImageView(view.getContext());

            Uri uri = Uri.parse(topimageUrls.get(i));
            SimpleDraweeView draweeView =new SimpleDraweeView(view.getContext());
            draweeView.setImageURI(uri);
            btn1.setBackground(draweeView.getDrawable());


/*
           Picasso.get().load(topimageUrls.get(i)).into(new Target(){


                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    btn1.setBackground(new BitmapDrawable(view.getContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }


                @Override
                public void onPrepareLoad(final Drawable placeHolderDrawable) {
                }
            });*/
            String str1=String.valueOf(topimageUrls.get(i));
            int m=str1.length();
            int n=m-38;
            String str=str1.substring(34,34+n);

            final int finalI = Integer.valueOf(str)-1;
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExampleAdapter mAdapter;
                    mAdapter=new ExampleAdapter(NotificationsFragment.exampleList);
                    String imageUrl=NotificationsFragment.exampleList.get(finalI).getmImageUrl();
                    String title=NotificationsFragment.exampleList.get(finalI).getmText1();
                    String description=NotificationsFragment.exampleList.get(finalI).getmText2();
                    double x=NotificationsFragment.exampleList.get(finalI).getmX();
                    double y=NotificationsFragment.exampleList.get(finalI).getmY();
                    String phone=NotificationsFragment.exampleList.get(finalI).getmPhoneNumber();
                    String alltext=NotificationsFragment.exampleList.get(finalI).getmAllText();
                    Intent intent=new Intent(getContext(),MoreInfoActivity.class);
                    intent.putExtra("imageUrl",imageUrl);
                    intent.putExtra("title",title);
                    intent.putExtra("description",description);
                    intent.putExtra("x",x);
                    intent.putExtra("y",y);
                    intent.putExtra("phone",phone);
                    intent.putExtra("alltext",alltext);
                    getContext().startActivity(intent);


                }
            });

            linearLayout1.addView(btn1);
        }






        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = view.getContext().registerReceiver(null, ifilter);


        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;


        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

       int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level *100 / (float)scale;




        TextView bateryText=(TextView)view.findViewById(R.id.textWelcom);
        int batteryPct1= (int) batteryPct;
        String str = String.valueOf(batteryPct1);
        bateryText.setText("Welcom QI");
        Paper.init(view.getContext());
        String language=Paper.book().read("language");
        TextView tt=(TextView)view.findViewById(R.id.texttokos);
        tt.setText(str+"%");
        if(language.equals("hy")){
            TextView txt1=(TextView)view.findViewById(R.id.newstext);
            TextView txt2=(TextView)view.findViewById(R.id.textTop);
            txt1.setText("Արդեն մեզ հետ են");
            txt2.setText("Լավագույն առաջարկներ");
        }
        if(batteryPct>=0 && batteryPct<20){
            if(language.equals("hy")){
                bateryText.setText("«Քյուայ» բաժնում գտեք Ձեզ հարմար սրճարան/ռեստորանը և վայելեք");
            }else bateryText.setText("In the field Qi find nearest restaurant/bar and enjoy");

        } else  if(batteryPct>=20 && batteryPct<50){
            if(language.equals("hy")){
                bateryText.setText("Դուք ունեք մեկ ժամ՝ հեռախոսը լիցքավորելու համար");
            }else bateryText.setText("You have a hour to charge your phone");


        } else  if(batteryPct>=50 && batteryPct<80){
            if(language.equals("hy")){
                bateryText.setText("Մտածեք հեռախոսը լիցքավորելու մասին, համատեղելով տաք թեյի հետ");
            }else bateryText.setText("Think about charging phone, combining with hot tea");


        } else  if(batteryPct>=80){

            if(language.equals("hy")){
                bateryText.setText("Հեռախոսը չունի լիցքավորման կարիք, սակայն մեկ բաժակ սուրճը չի խանգարի ։)");
            }else bateryText.setText("You don't need to charge phone, but a cup of coffee whill not hurt :)");


        }














      /*  rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.start();


rocketImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rocketAnimation.stop();
        rocketAnimation.start();
    }
});
*/

        return view;
    }


}
