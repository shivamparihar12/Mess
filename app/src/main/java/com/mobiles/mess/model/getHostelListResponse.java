package com.mobiles.mess.model;

import com.google.gson.annotations.SerializedName;

public class getHostelListResponse {
    @SerializedName("id")
    private String hostelID;

    @SerializedName("name")
    private String hostelName;

    public String getHostelID() {
        return hostelID;
    }

    public String getHostelName() {
        return hostelName;
    }

    public getHostelListResponse(String hostelID, String hostelName) {
        this.hostelID = hostelID;
        this.hostelName = hostelName;
    }
}
