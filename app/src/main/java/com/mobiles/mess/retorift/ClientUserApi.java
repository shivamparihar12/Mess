package com.mobiles.mess.retorift;

import com.mobiles.mess.model.Breakfast;
import com.mobiles.mess.model.OneDayData;
import com.mobiles.mess.model.getHostelListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientUserApi {

    //    @FormUrlEncoded
    @GET("getAllHostel.php")
    Call<ArrayList<getHostelListResponse>> getHostelList();

    @FormUrlEncoded
    @POST("getTotalRegStud.php")
    Call<ArrayList> getTotalRegStudent(@Field("current_hostel_id") String currentHostelId);

    @FormUrlEncoded
    @POST("getOneDayData.php")
    Call<ArrayList<OneDayData>> getOneDayData(@Field("current_date") String currentDate,
                                   @Field("current_hostel_id") String currentHostelID);
}
