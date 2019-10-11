package com.nested.Qi;

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

public class TopQi extends AsyncTask<String,Integer,Boolean> {


    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    private Context mContext;

    TopQi(Context context) {
        mContext = context;
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            if (Build.VERSION.SDK_INT >= 15) {
                StrictMode.ThreadPolicy policy = new
                        StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            URL url = new URL("https://mysmartech.ru/Qi/count.php");
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String Line = "";
            while (Line != null) {
                Line = bufferedReader.readLine();
                data = data + Line;
            }
            JSONArray JA1 = new JSONArray(data);
            for (int i = 0; i < JA1.length(); i++) {
                JSONObject JO1 = (JSONObject) JA1.get(i);

                    String urlImage = "https://mysmartech.ru/Qi/Qitop/top" + String.valueOf(JO1.get("count")) + ".png";
                    DashboardFragment.topimageUrls.add(urlImage);





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

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}

