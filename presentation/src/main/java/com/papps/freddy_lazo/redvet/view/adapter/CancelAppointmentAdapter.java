package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CancelAppointmentModel;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelAppointmentAdapter extends RecyclerView.Adapter<CancelAppointmentAdapter.CancelViewHolder> {

    private List<CancelAppointmentModel> data = new ArrayList<>();
    private OnClickAdapter listener;

    @Inject
    public CancelAppointmentAdapter() {
    }

    @NonNull
    @Override
    public CancelAppointmentAdapter.CancelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cancel_appointment, viewGroup, false);
        return new CancelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CancelAppointmentAdapter.CancelViewHolder holder, int i) {
        holder.bind(i);
    }

    public void setView(BaseActivity activity) {
        listener = (OnClickAdapter) activity;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<CancelAppointmentModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    class CancelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.tv_text)
        TextView tvText;

        CancelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void itemClicked() {
            itemSelected(getAdapterPosition());
            listener.data(data.get(getAdapterPosition()));
            bindList(data);
        }

        public void bind(int position) {
            tvText.setText(data.get(position).getText());
            ivCheck.setImageResource(data.get(position).isSelected() ? R.drawable.ic_check_green : R.drawable.ic_check_gray);
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

    public interface OnClickAdapter {
        void data(CancelAppointmentModel data);
    }
}
