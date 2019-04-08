package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetLoverPetsAdapter extends RecyclerView.Adapter<PetLoverPetsAdapter.PetLoverPetsViewHolder> {

    private List<PetLoverRegisterModel> data = new ArrayList<>();
    private Context context;

    @Inject
    PetLoverPetsAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public PetLoverPetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pet_lover_pets, viewGroup, false);
        context = view.getContext();
        return new PetLoverPetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetLoverPetsViewHolder holder, int i) {
        holder.bindData(i);
    }

    public void bindList(List<PetLoverRegisterModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public List<PetLoverRegisterModel> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PetLoverPetsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pet_name)
        TextView tvPetName;
        @BindView(R.id.img_check)
        ImageView imgCheck;

        PetLoverPetsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(int position) {
            tvPetName.setText(data.get(position).getName());
            if (data.get(position).isSelected()) {
                imgCheck.setImageResource(R.drawable.ic_check_green);
            } else {
                imgCheck.setImageResource(R.drawable.ic_check_gray);
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
