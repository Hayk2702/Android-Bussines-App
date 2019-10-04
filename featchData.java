package com.nested.Qi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class featchData extends AsyncTask<String,Integer,Boolean> {


    String data="";
    String dataParsed="";
    String singleParsed="";
    ProgressDialog progressDialog;
    private Context mContext;

    featchData (Context context){
        mContext = context;
    }



        @Override
    protected Boolean doInBackground(String... strings) {
            try {
                if (Build.VERSION.SDK_INT >=15)
                {
                    StrictMode.ThreadPolicy policy = new
                            StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                URL url=new URL("https://mysmartech.ru/Qi/android.php");
                HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String Line="";
                while (Line!=null){
                    Line=bufferedReader.readLine();
                    data=data+Line;
                }
                JSONArray JA=new JSONArray(data);
                for(int i=0;i<JA.length();i++){
                   // myLongRunningOperation();
                    JSONObject JO=(JSONObject) JA.get(i);
                    singleParsed="ID:"+JO.get("ID")+"\n"+"Title:"+JO.get("Title")+"\n";
                    dataParsed=dataParsed+singleParsed;



                    /*ImageView imageView=(ImageView) rootView.findViewById(R.id.imageViewImage);
                    Picasso.get().load("https://mysmartech.ru/Qi/img1.png").into(imageView); */

        String urlImage="https://mysmartech.ru/Qi/img"+Integer.toString(i+1)+".png";
                    NotificationsFragment.exampleList.add(new ExampleItem(JO.get("Title").toString(), JO.get("Description").toString(),40,20,"tel:"+JO.get("PhoneNumber").toString(),urlImage,JO.get("Text").toString()));

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
      //  super.onPostExecute(aVoid);
        NotificationsFragment.data=this.data;
       /* if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }*/
    }


    @Override
    protected void onPreExecute() {
       super.onPreExecute();
       /* progressDialog = new ProgressDialog(mContext);

        progressDialog = new ProgressDialog(mContext);
       progressDialog.setMessage("Please Wait");
        //progressDialog.setCancelable(false);
        progressDialog.show();*/
    }
 /*   private void myLongRunningOperation(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}

