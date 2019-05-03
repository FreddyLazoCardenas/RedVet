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
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.NotificationModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private List<NotificationModel> data = new ArrayList<>();
    private Context context;
    private OnClickAdapter listener;

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

    public void setView(BaseFragment fragment) {
        listener = (OnClickAdapter) fragment;
    }

    public void deleteNotification(NotificationModel data) {
        int index = this.data.indexOf(data);
        this.data.remove(index);
        notifyItemRemoved(index);
    }


    class NotificationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView tvTitle;
        @BindView(R.id.txt_content)
        TextView tvContent;
        @BindView(R.id.txt_time)
        TextView tvTime;
        @BindView(R.id.img_news)
        ImageView ivIcon;

        NotificationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void itemClick() {
            listener.dataNotification(data.get(getAdapterPosition()));
        }

        @SuppressLint("StringFormatMatches")
        public void bind(int position) {
            itemView.setBackgroundColor(ContextCompat.getColor(context, position % 2 == 0 ? R.color.colorWhite : R.color.colorNotificationGray));
            tvTitle.setText(getTitleFromType(data.get(position).getType()));
            tvContent.setText(data.get(position).getMessage());
            Long tsLong = System.currentTimeMillis() / 1000;
            Long notTime = Long.valueOf(data.get(position).getTime());
            long test = (tsLong - notTime) / 3600;
            if (test < 24) {
                tvTime.setText(context.getString(R.string.notification_time, test, test > 1 ? "horas" : "hora"));
            } else {
                tvTime.setText(context.getString(R.string.notification_time, test / 24, (test / 24) > 1 ? "dias" : "dia"));
            }
            ivIcon.setImageResource(getImageFromTitle(data.get(position).getType()));
        }

        private String getTitleFromType(String type) {
            switch (type) {
                case "PET_LOVER_APPOINTMENT_CONFIRMED":
                    return context.getString(R.string.pet_lover_appointment_confirmed);
                case "PET_LOVER_APPOINTMENT_CANCELED":
                    return context.getString(R.string.pet_lover_appointment_canceled);
                case "PET_LOVER_APPOINTMENT_FINISHED":
                    return context.getString(R.string.pet_lover_appointment_finished);
                case "PET_LOVER_NEW_MESSAGE":
                    return context.getString(R.string.red_vet_new_message);
                case "DOCTOR_NEW_MESSAGE":
                    return context.getString(R.string.red_vet_new_message);
                case "DOCTOR_APPOINTMENT_QUALIFIED":
                    return context.getString(R.string.doctor_appointment_qualified);
                case "DOCTOR_APPOINTMENT_CANCELED":
                    return context.getString(R.string.pet_lover_appointment_canceled);
                case "DOCTOR_NEW_APPOINTMENT":
                    return context.getString(R.string.doctor_new_appointment);
                case "DOCTOR_UNAVAILABLE":
                    return context.getString(R.string.doctor_unavailable);
                case "DOCTOR_AVAILABLE":
                    return context.getString(R.string.doctor_available);
                case "EMAIL_VERIFIED":
                    return context.getString(R.string.red_vet_email_verified);
                default:
                    return type;
            }
        }

        private int getImageFromTitle(String type) {
            switch (type) {
                case "PET_LOVER_APPOINTMENT_CONFIRMED":
                    return R.drawable.ic_check_not;
                case "PET_LOVER_APPOINTMENT_CANCELED":
                    return R.drawable.ic_exclamation_mark;
                case "PET_LOVER_APPOINTMENT_FINISHED":
                    return R.drawable.ic_check_not;
                case "PET_LOVER_NEW_MESSAGE":
                    return R.drawable.ic_love_not;
                case "DOCTOR_NEW_MESSAGE":
                    return R.drawable.ic_love_not;
                case "DOCTOR_APPOINTMENT_QUALIFIED":
                    return R.drawable.ic_love_not;
                case "DOCTOR_APPOINTMENT_CANCELED":
                    return R.drawable.ic_exclamation_mark;
                case "DOCTOR_NEW_APPOINTMENT":
                    return R.drawable.ic_love_not;
                case "DOCTOR_UNAVAILABLE":
                    return R.drawable.ic_exclamation_mark;
                case "DOCTOR_AVAILABLE":
                    return R.drawable.ic_check_not;
                case "EMAIL_VERIFIED":
                    return R.drawable.ic_love_not;
                default:
                    return R.drawable.ic_check_not;
            }
        }
    }

    public interface OnClickAdapter {
        void dataNotification(NotificationModel data);
    }
}
