package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.RedVetMessageModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_PET_LOVER = 0;
    private static final int VIEW_TYPE_DOCTOR = 1;
    private List<RedVetMessageModel> data = new ArrayList<>();

    @Inject
    public MessagesAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_PET_LOVER) {
            return new PetLoverMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pet_lover_message, viewGroup, false));
        } else {
            return new DoctorMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doctor_message, viewGroup, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        RedVetMessageModel model = data.get(position);
        if (model.getFrom().equals("pet_lover")) {
            return VIEW_TYPE_PET_LOVER;
        } else {
            return VIEW_TYPE_DOCTOR;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PetLoverMessageViewHolder) {
            PetLoverMessageViewHolder petLoverMessageViewHolder = (PetLoverMessageViewHolder) holder;
            petLoverMessageViewHolder.bind(position);
        } else {
            DoctorMessageViewHolder doctorMessageViewHolder = (DoctorMessageViewHolder) holder;
            doctorMessageViewHolder.bind(position);
        }
    }

    public void bindList(List<RedVetMessageModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addMessage(RedVetMessageModel message) {
        if (data != null) {
            this.data.add(message);
            notifyItemInserted(data.size() - 1);
        }
    }

    public class DoctorMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message)
        TextView tvMessage;

        DoctorMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvMessage.setText(data.get(position).getMessage());
        }
    }

    public class PetLoverMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message)
        TextView tvMessage;

        PetLoverMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvMessage.setText(data.get(position).getMessage());
        }
    }

}
