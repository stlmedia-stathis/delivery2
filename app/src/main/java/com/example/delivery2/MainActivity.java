package com.example.delivery2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private
    Button BtnMenu;
    String apotelesma, type;
    ProgressDialog progress;
    ArrayList<String> ALapotelesmaData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnMenu = findViewById(R.id.btnmenu);
        progress = new ProgressDialog(this);
        progress.setTitle("Φόρτωση");
        progress.setMessage("Περιμένετε κατά τη φόρτωση...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
    protected void onStart()
    {

        super.onStart();
        showpelatis();
    }

    public void btnmenuclick(View view) {
        Intent myIntent;
        myIntent = new Intent(MainActivity.this,
                menu.class);
        startActivity(myIntent);
    }

    private class InsertEntoliSQL extends AsyncTask<String,Void,String> {
        Context context;
        InsertEntoliSQL(Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            type = params[0];
            String login_url = params[2];
            if (type.equals("insertentoli") )try {
                String key = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line = "";
                apotelesma = "";
                while ((line = bufferedReader.readLine()) != null) {
                    apotelesma += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return apotelesma;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String Apotelesma) {
          readapotelesma(apotelesma);
            //   listentoliData();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void readapotelesma(String Apotelesma) {
        ALapotelesmaData.clear();
        Globals g = (Globals) getApplication();
        String text1;
        text1 = "";
        for (int i = 0; i < Apotelesma.length(); i++) {
            if (Apotelesma.charAt(i) != '*' && Apotelesma.charAt(i) != '@') {
                text1 = text1 + Apotelesma.charAt(i);
            } else if (Apotelesma.charAt(i) == '*') {
                text1 = text1 + " \n";
            } else if (Apotelesma.charAt(i) == '@') {
              ALapotelesmaData.add(text1);
                text1 = "";
            }
        }
        g.setArraylist(ALapotelesmaData);
        progress.dismiss();
    }

    private void showpelatis(){
        MainActivity.InsertEntoliSQL SQL = new MainActivity.InsertEntoliSQL(this);
        SQL.execute("insertentoli","PASS1234","https://www.stlmedia.gr/aps/deliveri_selectkatigoria.php");
    }
}