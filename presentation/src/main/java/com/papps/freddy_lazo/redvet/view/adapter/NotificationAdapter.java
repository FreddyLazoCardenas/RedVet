package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private List<NotificationModel> data = new ArrayList<>();

    @Inject
    NotificationAdapter() {
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);
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

        public void bind(int position) {
            tvTitle.setText(data.get(position).getType());
            tvContent.setText(data.get(position).getMessage());
            tvTime.setText(data.get(position).getTime());
        }
    }
}
