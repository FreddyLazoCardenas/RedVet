package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<DoctorAppointmentModel> data = new ArrayList<>();
    private List<DoctorAppointmentModel> filterData = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;
    private boolean isFiltering;

    @Inject
    public AppointmentAdapter() {
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quote, viewGroup, false);
        context = view.getContext();
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return isFiltering ? filterData.size() : data.size();
    }

    public void bindList(List<DoctorAppointmentModel> data) {
        if (data != null)
            this.data = data;
        notifyDataSetChanged();
    }

    public void setFiltering(boolean isFiltering) {
        this.isFiltering = isFiltering;
    }

    public boolean isFiltering() {
        return isFiltering;
    }

    public void bindFilterList(String appointmentStatus) {
        filterData.clear();
        for (DoctorAppointmentModel model : data) {
            if (model.getStatus().equals(appointmentStatus)) {
                filterData.add(model);
            }
        }
        notifyDataSetChanged();
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
    }

    public void removeAppointment(int id) {
        int index = getItemIndex(id);
        data.remove(index);
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            notifyItemRemoved(filteringIndex);
        } else {
            notifyItemRemoved(index);
        }
    }

    private int getFilterItemIndex(int id) {
        for (DoctorAppointmentModel model : filterData) {
            if (model.getId() == id) {
                return filterData.indexOf(model);
            }
        }
        return 0;
    }

    private int getItemIndex(int id) {
        for (DoctorAppointmentModel model : data) {
            if (model.getId() == id) {
                return data.indexOf(model);
            }
        }
        return 0;
    }

    public void doctorPendingAppointment(int id) {
        int index = getItemIndex(id);
        DoctorAppointmentModel model = data.get(index);
        model.setStatus("confirmed");
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            notifyItemRemoved(filteringIndex);
        } else {
            notifyItemChanged(index);
        }
    }

    public void doctorConfirmAppointment(int id) {
        int index = getItemIndex(id);
        DoctorAppointmentModel model = data.get(index);
        model.setStatus("finished");
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            notifyItemRemoved(filteringIndex);
        } else {
            notifyItemChanged(index);
        }
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView tvDate;
        @BindView(R.id.txt_time)
        TextView tvTime;

        AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            DoctorAppointmentModel bindData = isFiltering ? filterData.get(position) : data.get(position);
            tvDate.setText(bindData.getDate());
            tvTime.setText(bindData.getTime());
        }

        @OnClick
        void itemClicked() {
            listener.itemClicked(isFiltering ? filterData.get(getAdapterPosition()) : data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(DoctorAppointmentModel data);
    }
}
