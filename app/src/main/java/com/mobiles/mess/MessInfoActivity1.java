package com.mobiles.mess;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mobiles.mess.adapter.MessInfoAdapter;
import com.mobiles.mess.databinding.ActivityMessInfo1Binding;
import com.mobiles.mess.model.OneDayData;
import com.mobiles.mess.retorift.RetrofitClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessInfoActivity1 extends AppCompatActivity {
    private ActivityMessInfo1Binding binding;
    private String currentHostelID;
    private ArrayList<OneDayData> oneDayDataArrayList;
    boolean runanotherloop=false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessInfo1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        oneDayDataArrayList = new ArrayList<>();
        currentHostelID = (String) getIntent().getExtras().get("hostelID");
        String hostelName = (String) getIntent().getExtras().get("hostelName");
        getSupportActionBar().setTitle("MESS: " + hostelName);

        showTotalRegStudent(currentHostelID);

        getSevenDayData(getCurrentDate());
        Log.d("CurrentDAte",getCurrentDate());

        // for (int i=0;i<7;i++){
//            Log.d("1checking",oneDayDataArrayList.get(i).getBreakfast());
//        }
    }

    public String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getSevenDayData(String actualCurrentDate) {
        for (int i = 7; i >= 0; i--) {
            binding.progressCircular.setVisibility(View.VISIBLE);
            //LocalDate date = LocalDate.parse("2022-03-07");
            Log.d("Actualdate",actualCurrentDate);
            LocalDate date = LocalDate.parse(actualCurrentDate);
            LocalDate currentDate = date.minusDays(i);
            Log.d("currentDate",String.valueOf(currentDate));
            getOneDayData(String.valueOf(currentDate), currentHostelID);
            binding.progressCircular.setVisibility(View.GONE);
            if (i==0){
                for (int j = 1; j <=14; j++) {
                    binding.progressCircular.setVisibility(View.VISIBLE);
                    //LocalDate date = LocalDate.parse("2022-03-07");
//                    Log.d("Actualdate",actualCurrentDate);
//                    LocalDate date = LocalDate.parse(actualCurrentDate);
                     LocalDate currentDateNew = date.plusDays(j);
                    Log.d("currentDateNew",String.valueOf(currentDateNew));

                    getOneDayData(String.valueOf(currentDateNew), currentHostelID);
                    binding.progressCircular.setVisibility(View.GONE);

                }
            }
        }
    }

    public void getOneDayData(String currentDate, String currentHostelID) {
        Call<ArrayList<OneDayData>> oneDayDataCall = RetrofitClient.getService().getOneDayData("'" + currentDate + "'", currentHostelID);
        oneDayDataCall.enqueue(new Callback<ArrayList<OneDayData>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ArrayList<OneDayData>> call, Response<ArrayList<OneDayData>> response) {
                ArrayList<OneDayData> oneDayData = response.body();
                OneDayData dayData = oneDayData.get(0);
                oneDayDataArrayList.add(dayData);
                Log.d("checking", dayData.getBreakfast());

                if (oneDayDataArrayList.size() == 21) {
                    oneDayDataArrayList.sort(new Comparator<OneDayData>() {
                        @Override
                        public int compare(OneDayData oneDayData, OneDayData t1) {
                            return oneDayData.getCurrentDate().compareTo(t1.getCurrentDate());
                        }
                    });

                    //sort(oneDayDataArrayList);



                    MessInfoAdapter messInfoAdapter = new MessInfoAdapter(oneDayDataArrayList, getApplicationContext(), getCurrentDate());
                    binding.recyclerView.setAdapter(messInfoAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.recyclerView.setLayoutManager(linearLayoutManager);
                    for (int i = 0; i < 7; i++) {
                        Log.d("1checking", oneDayDataArrayList.get(i).getCurrentDate());
                    }

                    runanotherloop=true;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OneDayData>> call, Throwable t) {
                Toast.makeText(MessInfoActivity1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showTotalRegStudent(String hostelID) {
        Call<ArrayList> getTotalRegStudCall = RetrofitClient.getService().getTotalRegStudent(hostelID);
        getTotalRegStudCall.enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call<ArrayList> call, Response<ArrayList> response) {
                ArrayList arrayList = response.body();
                binding.totalRegStud.setText(valueOf(arrayList.get(0)));
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {
                Toast.makeText(MessInfoActivity1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //Log.d(TAG, t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mess_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                getSevenDayData(getCurrentDate());
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }


}