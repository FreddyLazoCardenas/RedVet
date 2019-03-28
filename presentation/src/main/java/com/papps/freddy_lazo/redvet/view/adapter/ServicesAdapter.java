package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private List<ServicesModel> data = new ArrayList<>();

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

    class ServicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_services)
        TextView txtServices;


        ServicesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            txtServices.setText(data.get(position).getName());
        }
    }

    private int getImage(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_dog;
            case 1:
                return R.drawable.ic_cat;
            case 2:
                return R.drawable.ic_fish;
            case 3:
                return R.drawable.ic_bird;
            case 4:
                return R.drawable.ic_rabbit;
            case 5:
                return R.drawable.ic_hedgehog;
            default:
                return R.drawable.ic_dog;

        }
    }
}
