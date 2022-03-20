package com.example.delivery2;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;

public class Globals extends Application {
private
    ArrayList<String> myData = new ArrayList<>();

    public void setArraylist (String data){
        this.myData.add(data);
    }

    public ArrayList getArraylist() {
        return this.myData;
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}