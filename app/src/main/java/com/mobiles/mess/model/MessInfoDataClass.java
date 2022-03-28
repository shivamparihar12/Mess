package com.mobiles.mess.model;

public class MessInfoDataClass {
    private String CurrentDate;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String leaves;

    public MessInfoDataClass(String currentDate, String breakfast, String lunch, String dinner, String leaves) {
        CurrentDate = currentDate;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.leaves = leaves;
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

    public String getLeaves() {
        return leaves;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }
}
