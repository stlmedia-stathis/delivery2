package com.example.delivery2;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Arrays;

public class menu extends AppCompatActivity implements ItemClickListener {
    private
    ArrayList<String> myListData = new ArrayList<String>();
    String apotelesma, type;
    RecyclerView recyclerView;
    MyListAdapter adapter;
    Globals g = (Globals) getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Globals g = (Globals) getApplication();
        setContentView(R.layout.menu);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
      //  MyListAdapter customAdapter = new MyListAdapter(menu.this, g.getArraylist());
        MyListAdapter customAdapter = new MyListAdapter(menu.this, g.getArraylist());
       // customAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        customAdapter.setClickListener(this);
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

    private void listentoliData() {

        // adapter = new MyListAdapter(myListData);
     //   recyclerView.setHasFixedSize(true);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //    recyclerView.setAdapter(adapter);
      //  LinearLayoutManager horizontalLayoutManager
      //          = new LinearLayoutManager(menu.this, LinearLayoutManager.HORIZONTAL, false);
      //  recyclerView.setLayoutManager(horizontalLayoutManager);
    }

    private void readapotelesma(String Apotelesma) {
        String text1;
        text1 = "";
       // myListData.clear();
        for (int i = 0; i < 50; i++) {
            if (Apotelesma.charAt(i) != '*' && Apotelesma.charAt(i) != '@') {
                text1 = text1 + Apotelesma.charAt(i);
            } else if (Apotelesma.charAt(i) == '*') {
                text1 = text1 + " \n";
            } else if (Apotelesma.charAt(i) == '@') {
                myListData.add(text1);
                text1 = "";
            }
        }

    }

    private void showpelatis(){
            menu.InsertEntoliSQL SQL = new menu.InsertEntoliSQL(this);
            SQL.execute("insertentoli","PASS1234","https://www.melikriton.gr/eshopmanager/w_order_selectpelati.php");
        }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view, String position) {
toastMessage(position);
    }
}