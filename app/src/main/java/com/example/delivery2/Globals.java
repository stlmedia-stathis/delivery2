package com.example.delivery2;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;

public class Globals extends Application {
private
    String status;
    String PelatisId, Pelatis, ParageliaId;
    ArrayList<String> myData = new ArrayList<>();

    public void setPelatisID(String PelatisId) {
        this.PelatisId = PelatisId;
    }

    public void setLocalStatus(String status) {
        this.status = status;}

    public String getLocalStatus() {return this.status;}

    public String getParageliaId() {return this.ParageliaId;}

    public void setParageliaID(String ParageliaId) {
        this.ParageliaId = ParageliaId;
    }

    public void setPelatis(String Pelatis) {
        this.Pelatis = Pelatis;
    }

    public String getPelatisId() {
        return this.PelatisId;
    }

    public String getPelatis() {
        return this.Pelatis;
    }

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