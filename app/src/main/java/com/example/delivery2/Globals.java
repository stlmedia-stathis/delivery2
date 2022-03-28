package com.example.delivery2;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {
private
   ArrayList<String> myData = new ArrayList<>();

    public void setArraylist (ArrayList data){
        this.myData = data;
    }

    public ArrayList getArraylist() {
        return this.myData;
    }

}