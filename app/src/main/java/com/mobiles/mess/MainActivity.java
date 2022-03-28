package com.mobiles.mess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobiles.mess.databinding.ActivityMainBinding;
import com.mobiles.mess.model.getHostelListResponse;
import com.mobiles.mess.retorift.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private final String TAG = "MainActivity";
    private ArrayList<getHostelListResponse> getHostelListResponseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showHostelList();
    }

    public void showHostelList() {
        Call<ArrayList<getHostelListResponse>> getHostelListCall = RetrofitClient.getService().getHostelList();
        getHostelListCall.enqueue(new Callback<ArrayList<getHostelListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<getHostelListResponse>> call, Response<ArrayList<getHostelListResponse>> response) {
                getHostelListResponseArrayList = response.body();
                addRadioButton();

                //Adding RadioButtons dynamically
//                binding.RadioGroup.setOrientation(LinearLayout.HORIZONTAL);
//                for (int i = 1; i <= getHostelListResponseArrayList.size(); i++) {
//                    RadioButton radioButton = new RadioButton(MainActivity.this);
//                    radioButton.setId(i);
//                    getHostelListResponse hostelListResponse = getHostelListResponseArrayList.get(i);
//                    radioButton.setText(hostelListResponse.getHostelName());
//                    radioButton.setOnClickListener(MainActivity.this);
//                    binding.RadioGroup.addView(radioButton);
//                    Log.d(TAG,"radio button added"+i);
//                }
            }

            @Override
            public void onFailure(Call<ArrayList<getHostelListResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.getMessage());
            }
        });
    }

    public void addRadioButton() {
        Log.d(TAG,"addRadioButtonFunction was called");
        binding.RadioGroup.setOrientation(LinearLayout.VERTICAL);
        for (int i = 1; i <= getHostelListResponseArrayList.size(); i++) {
            RadioButton radioButton = new RadioButton(MainActivity.this);
            radioButton.setId(i);
            getHostelListResponse hostelListResponse = getHostelListResponseArrayList.get(i-1);
            radioButton.setText(hostelListResponse.getHostelName());
            radioButton.setOnClickListener(MainActivity.this);
            binding.RadioGroup.addView(radioButton);
            Log.d(TAG, "radio button added" + i);
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        Intent intent= new Intent(this,MessInfoActivity1.class);
        intent.putExtra("hostelID", String.valueOf(view.getId()));
        intent.putExtra("hostelName", getHostelListResponseArrayList.get(view.getId()-1).getHostelName());
        Log.d(TAG,String.valueOf(view.getId()));
        startActivity(intent);
    }
}