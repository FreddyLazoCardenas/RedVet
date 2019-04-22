package com.papps.freddy_lazo.redvet.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.NotificationModel;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private List<NotificationModel> data = new ArrayList<>();
    private Context context;

    @Inject
    NotificationAdapter() {
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);
        context = view.getContext();
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<NotificationModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    class NotificationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView tvTitle;
        @BindView(R.id.txt_content)
        TextView tvContent;
        @BindView(R.id.txt_time)
        TextView tvTime;

        NotificationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("StringFormatMatches")
        public void bind(int position) {
            itemView.setBackgroundColor(ContextCompat.getColor(context, position % 2 == 0 ? R.color.colorWhite : R.color.colorNotificationGray));
            tvTitle.setText(data.get(position).getType());
            tvContent.setText(data.get(position).getMessage());
            Long tsLong = System.currentTimeMillis() / 1000;
            Long notTime = Long.valueOf(data.get(position).getTime());
            long test = (tsLong - notTime) / 3600;
            if (test < 24) {
                tvTime.setText(context.getString(R.string.notification_time,test,"horas"));
            }else{
                tvTime.setText(context.getString(R.string.notification_time,test/24,"dias"));
            }
        }
    }
}
