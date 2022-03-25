package com.example.delivery2;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {
private
   ArrayList<String> myData = new ArrayList<>();
//ArrayList<ArrayList<String>> myData = new ArrayList<>();
  //  ArrayList<String> row = new ArrayList<>();

    public void setArraylist (String data, Integer data1){
        ArrayList<String> row = new ArrayList<>();
        this.myData.add(data+"+@"+data1);
       // row.add(data1.toString());
      //  this.myData.add(row);
    }

    public ArrayList getArraylist() {
        return this.myData;
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}