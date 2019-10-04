package com.nested.Qi;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import io.paperdb.Paper;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, null);
        Paper.init(view.getContext());
        String language=Paper.book().read("language");
        if(language.equals("hy")){
            TextView textView=(TextView)view.findViewById(R.id.textView7);
            textView.setText("«Քյուայ» ընկերության առաքելությունը  Ձեր ժամանցը առավելագույնս հարմարավետ դարձնելն է: Յուրաքանչյուրս պարբերաբար բախվում ենք չլիցքավորված հեռախոսի հետևանքով առաջացող անհարմար ու նյարդայնացնող խնդիրներին: Հենց այդ պատճառով «Քյուայ» ընկերությունը հանդես է գալիս հաճելի և օգտակար նախաձեռնությամբ՝  համալրել ժամանցի վայրերը արտաքին մարտկոցներով: Լինելով առաջինը Հայաստանում «Քյուայ»-ը կառաջարկի մենյու մարտկոցներ, անլար արտաքին մարտկոցներ Ձեր հանգիստը կամ գործնական հանդիպումը կատարյալ հարմարավետ դարձնելու համար։\n" +
                    "Մեզ կարող եք գտնել հետևյալ ծրագրային հավելվածում նշված սրճարան/ռեստորաններ -ում:\n" +
                    "Հոգատարությամբ՝\n" +
                    "«Քյուայ»");
        }

        Button button=view.findViewById(R.id.call_about);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone="tel:+37412606909";
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
        Button button1=(Button)view.findViewById(R.id.inst);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/qi_charge");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/qi_charge")));
                }

            }
        });
        Button button2=(Button)view.findViewById(R.id.face);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String facebookUrlScheme = "fb://page/106572510721638";

                try {
                    int versionCode = v.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;

                    if (versionCode >= 3002850) {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/qi.armenia");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrlScheme)));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/qi.armenia")));

                }

            }
        });


        return view;
    }


}
