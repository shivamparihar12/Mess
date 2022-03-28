package com.mobiles.mess.model;

import com.google.gson.annotations.SerializedName;

public class Breakfast {
    @SerializedName("count")
    private String count;
    @SerializedName("date")
    private String date;

    public String getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public Breakfast(String count, String date) {
        this.count = count;
        this.date = date;
    }


//    public Breakfast(String c=, String totalNO) {
//        this.currentDate = currentDate;
//        this.totalNO = totalNO;
//    }
//
//    public String getCurrentDate() {
//        return currentDate;
//    }
//
//    public String getTotalNO() {
//        return totalNO;
//    }
}
