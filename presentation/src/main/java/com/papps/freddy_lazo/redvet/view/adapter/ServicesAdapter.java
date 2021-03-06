package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.ServicesModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private List<ServicesModel> data = new ArrayList<>();
    private boolean canClick = true;

    @Inject
    ServicesAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_services, viewGroup, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<ServicesModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }


    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public List<ServicesModel> getData() {
        return data;
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_services)
        TextView txtServices;
        @BindView(R.id.img_check)
        ImageView imgCheck;


        ServicesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            txtServices.setText(data.get(position).getName());
            if (data.get(position).getState()) {
                imgCheck.setImageResource(R.drawable.ic_check_green);
            } else {
                imgCheck.setImageResource(R.drawable.ic_check_gray);
            }
        }

        private void itemSelected(int adapterPosition) {
            for (int i = 0; i < data.size(); i++) {
                if (i != adapterPosition) {
                    // data.get(i).setSelected(false);
                } else {
                    data.get(i).setState(!data.get(i).getState());
                }
            }
        }

        @OnClick
        void serviceClicked() {
            if (canClick) {
                itemSelected(getAdapterPosition());
                bindList(data);
            }
        }
    }

}
