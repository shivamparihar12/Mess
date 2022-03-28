package com.mobiles.mess.model;

import com.google.gson.annotations.SerializedName;

public class OneDayData {
    @SerializedName("date")
    private String currentDate;

    @SerializedName("breakfast")
    private String breakfast;

    @SerializedName("lunch")
    private String lunch;

    @SerializedName("dinner")
    private String dinner;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("leaves")
    private String leaves;

    public String getLeaves() {
        return leaves;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public OneDayData(String currentDate, String breakfast, String lunch, String dinner, String timestamp, String leaves) {
        this.currentDate = currentDate;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.timestamp = timestamp;
        this.leaves = leaves;
    }
}
