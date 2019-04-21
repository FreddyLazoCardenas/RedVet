package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.ScheduleRegisterModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.ScheduleViewHolder> {

    private List<ScheduleRegisterModel> data = new ArrayList<>();
    private onClickAdapter listener;

    @Inject
    public SchedulesAdapter() {
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_schedules, viewGroup, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<ScheduleRegisterModel> data) {
        if (data != null)
            this.data = data;
        notifyDataSetChanged();
    }

    public List<ScheduleRegisterModel> getData(){
        return data;
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
    }

    public void updateData(ScheduleRegisterModel currentSchedule) {
        data.set(getItemIndex(currentSchedule.getDay()), currentSchedule);
    }

    private int getItemIndex(int id) {
        for (ScheduleRegisterModel model : data) {
            if (model.getDay() == id) {
                return data.indexOf(model);
            }
        }
        return -1;
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_schedule)
        TextView tvASchedule;


        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvASchedule.setText(data.get(position).getDayName());
            if (data.get(position).isSelected()) {
                tvASchedule.setAlpha(1f);
            } else {
                tvASchedule.setAlpha(0.3f);
            }
        }

        @OnClick
        void itemClicked() {
            itemSelected(getAdapterPosition());
            listener.itemClicked(data.get(getAdapterPosition()));
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

    public interface onClickAdapter {
        void itemClicked(ScheduleRegisterModel data);
    }
}
