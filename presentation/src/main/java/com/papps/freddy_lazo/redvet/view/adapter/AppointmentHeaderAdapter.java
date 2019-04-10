package com.papps.freddy_lazo.redvet.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.view.fragment.AppointmentFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentHeaderAdapter extends RecyclerView.Adapter<AppointmentHeaderAdapter.AppointmentViewHolder> {

    private List<CreateAppointmentObjectModel> data = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;

    @Inject
    AppointmentHeaderAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_appointment_header, viewGroup, false);
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

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
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
                tvAppointment.setBackground(ContextCompat.getDrawable(context, R.drawable.quote_bg_selected));
            } else {
                tvAppointment.setBackground(ContextCompat.getDrawable(context, R.drawable.quote_bg));
            }
        }

        @OnClick
        void itemClick() {
            itemSelected(getAdapterPosition());
            bindList(data);
            listener.data(data);
        }

        private void itemSelected(int adapterPosition) {
            for (int i = 0; i < data.size(); i++) {
                if (i != adapterPosition) {
                    data.get(i).setSelected(false);
                } else {
                    data.get(i).setSelected(!data.get(i).isSelected());
                }
            }
        }

    }

    public interface onClickAdapter {
        void data(List<CreateAppointmentObjectModel> data);
    }
}

