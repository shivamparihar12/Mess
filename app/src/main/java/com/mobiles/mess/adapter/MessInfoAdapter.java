package com.mobiles.mess.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiles.mess.R;
import com.mobiles.mess.model.OneDayData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class MessInfoAdapter extends RecyclerView.Adapter {
    ArrayList<OneDayData> arrayList;
    Context context;
    String currentDate;
    int GREY_LIST_VIEW_TYPE = 1;
    int WHITE_LIST_VIEW_TYPE = 2;
    int GREEN_LIST_VIEW_TYPE = 3;


    public MessInfoAdapter(ArrayList<OneDayData> arrayList, Context context, String currentDate) {
        this.arrayList = arrayList;
        this.context = context;
        this.currentDate = currentDate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == GREY_LIST_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.grey_mess_info_list_item, parent, false);
            return new greenViewHolder(view);
        } else if (viewType == WHITE_LIST_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.white_mess_list_item, parent, false);
            return new whiteViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.green_mess_info_list_item, parent, false);
            return new greenViewHolder(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OneDayData oneDayData = arrayList.get(position);
        String date = oneDayData.getCurrentDate();
        String[] arrOfString = date.split("'", 3);
        Log.d("equal", currentDate + " " + arrOfString[1]);
        Log.d("currentDate", currentDate);
        if (holder.getClass() == greyViewHolder.class) {
            ((greyViewHolder) holder).date.setText(getWeekDayName(arrOfString[1]).trim());
            ((greyViewHolder) holder).breakfast.setText(oneDayData.getBreakfast());
            ((greyViewHolder) holder).lunch.setText(oneDayData.getLunch());
            ((greyViewHolder) holder).dinner.setText(oneDayData.getDinner());
            ((greyViewHolder) holder).leaves.setText(oneDayData.getLeaves());
            Log.d("bind", oneDayData.getCurrentDate() + " " + oneDayData.getBreakfast() + "" + oneDayData.getLunch());
        } else if (holder.getClass() == whiteViewHolder.class) {
            ((whiteViewHolder) holder).date.setText(getWeekDayName(arrOfString[1]).trim());
            ((whiteViewHolder) holder).breakfast.setText(oneDayData.getBreakfast());
            ((whiteViewHolder) holder).lunch.setText(oneDayData.getLunch());
            ((whiteViewHolder) holder).dinner.setText(oneDayData.getDinner());
            ((whiteViewHolder) holder).leaves.setText(oneDayData.getLeaves());
            Log.d("bind", oneDayData.getCurrentDate() + " " + oneDayData.getBreakfast() + "" + oneDayData.getLunch());
        } else {
            ((greenViewHolder) holder).date.setText(getWeekDayName(arrOfString[1]).trim());
            ((greenViewHolder) holder).breakfast.setText(oneDayData.getBreakfast());
            ((greenViewHolder) holder).lunch.setText(oneDayData.getLunch());
            ((greenViewHolder) holder).dinner.setText(oneDayData.getDinner());
            ((greenViewHolder) holder).leaves.setText(oneDayData.getLeaves());
            Log.d("bind", oneDayData.getCurrentDate() + " " + oneDayData.getBreakfast() + "" + oneDayData.getLunch());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 7) {
            return GREEN_LIST_VIEW_TYPE;
        }
        if (position % 2 != 0 && !arrayList.get(position).getCurrentDate().equals(currentDate)) {
            return WHITE_LIST_VIEW_TYPE;
        } else {
            return GREY_LIST_VIEW_TYPE;
        }
    }

    public class greenViewHolder extends RecyclerView.ViewHolder {
        TextView date, breakfast, lunch, dinner, leaves;
        LinearLayout linearLayout;

        public greenViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
            breakfast = itemView.findViewById(R.id.breakfast_item);
            lunch = itemView.findViewById(R.id.lunch_item);
            dinner = itemView.findViewById(R.id.dinner_item);
            leaves = itemView.findViewById(R.id.onLeaves_item);
            linearLayout = itemView.findViewById(R.id.linearLayout_item);
        }
    }

    public class greyViewHolder extends RecyclerView.ViewHolder {
        TextView date, breakfast, lunch, dinner, leaves;
        LinearLayout linearLayout;

        public greyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
            breakfast = itemView.findViewById(R.id.breakfast_item);
            lunch = itemView.findViewById(R.id.lunch_item);
            dinner = itemView.findViewById(R.id.dinner_item);
            leaves = itemView.findViewById(R.id.onLeaves_item);
            linearLayout = itemView.findViewById(R.id.linearLayout_item);
        }
    }

    public class whiteViewHolder extends RecyclerView.ViewHolder {
        TextView date, breakfast, lunch, dinner, leaves;
        LinearLayout linearLayout;

        public whiteViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
            breakfast = itemView.findViewById(R.id.breakfast_item);
            lunch = itemView.findViewById(R.id.lunch_item);
            dinner = itemView.findViewById(R.id.dinner_item);
            leaves = itemView.findViewById(R.id.onLeaves_item);
            linearLayout = itemView.findViewById(R.id.linearLayout_item);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getWeekDayName(String s) {
        DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d", Locale.ENGLISH);
        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE, d MMM, yyyy", Locale.ENGLISH);
        return LocalDate.parse(s, dtfInput).format(dtfOutput);
    }
}
