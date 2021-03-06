package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentTypeAdapter extends RecyclerView.Adapter<AppointmentTypeAdapter.AppointmentViewHolder> {

    private List<CreateAppointmentObjectModel> data = new ArrayList<>();
    private Context context;

    @Inject
    AppointmentTypeAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_appointment, viewGroup, false);
        context = view.getContext();
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int i) {
        holder.bindData(i);
    }

    public void bindList(List<CreateAppointmentObjectModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }


    public List<CreateAppointmentObjectModel> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_appointment)
        TextView tvAppointment;

        AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(int position) {
            tvAppointment.setText(data.get(position).getName());
            if (data.get(position).isSelected()) {
                tvAppointment.setBackground(ContextCompat.getDrawable(context, R.drawable.appointment_bg_selected));
                tvAppointment.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            } else {
                tvAppointment.setBackground(ContextCompat.getDrawable(context, R.drawable.appointment_bg_not_selected));
                tvAppointment.setTextColor(ContextCompat.getColor(context, R.color.colorGray));
            }
        }

        @OnClick
        void itemClick() {
            itemSelected(getAdapterPosition());
            bindList(data);
        }

        private void itemSelected(int adapterPosition) {
            for (int i = 0; i < data.size(); i++) {
                if (i != adapterPosition) {
                    data.get(i).setSelected(false);
                } else {
                    data.get(i).setSelected(true);
                }
            }
        }

    }
}
