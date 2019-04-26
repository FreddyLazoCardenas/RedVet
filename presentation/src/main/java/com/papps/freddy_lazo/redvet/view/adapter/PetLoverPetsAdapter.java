package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetLoverPetsAdapter extends RecyclerView.Adapter<PetLoverPetsAdapter.PetLoverPetsViewHolder> {

    private List<PetLoverRegisterModel> data = new ArrayList<>();
    private List<PetLoverRegisterModel> filterData = new ArrayList<>();
    private Context context;
    private boolean isFiltering;

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
            if (isFiltering) {
                this.filterData = data;
            } else {
                this.data = data;
            }
            notifyDataSetChanged();
        }
    }

    public List<PetLoverRegisterModel> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return isFiltering ? filterData.size() : data.size();
    }

    public void filteringPets(List<PetRedVetModel> data) {
        List<Integer> ids = getIds(data);
        isFiltering = !ids.isEmpty();
        if (isFiltering) {
            fillFilterArray(ids);
        }
        notifyDataSetChanged();
    }

    private void fillFilterArray(List<Integer> ids) {
        filterData.clear();
        for (Integer id : ids) {
            for (PetLoverRegisterModel model : data) {
                if (model.getPet_id() == id) {
                    filterData.add(model);
                }
            }
        }
    }

    private List<Integer> getIds(List<PetRedVetModel> data) {
        List<Integer> ids = new ArrayList<>();
        for (PetRedVetModel model : data) {
            if (model.isSelected()) {
                ids.add(model.getId());
            }
        }
        return ids;
    }

    class PetLoverPetsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pet_name)
        TextView tvPetName;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.img_pet)
        ImageView ivPet;

        PetLoverPetsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(int position) {
            PetLoverRegisterModel bindData = isFiltering ? filterData.get(position) : data.get(position);
            tvPetName.setText(bindData.getName());
            loadImage(bindData.getPhoto_url());
            if (bindData.isSelected()) {
                imgCheck.setImageResource(R.drawable.ic_check_green);
            } else {
                imgCheck.setImageResource(R.drawable.ic_check_gray);
            }
        }

        private void loadImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_placeholder)
                    .load(photo != null ? photo : "")
                    .into(ivPet);
        }

        @OnClick
        void itemClick() {
            itemSelected(getAdapterPosition());
            bindList(isFiltering ? filterData : data);
        }

        private void itemSelected(int adapterPosition) {
            List<PetLoverRegisterModel> modelSel = isFiltering ? filterData : data;
            for (PetLoverRegisterModel model : data) {
                model.setSelected(false);
            }
            for (int i = 0; i < modelSel.size(); i++) {
                if (i != adapterPosition) {
                    modelSel.get(i).setSelected(false);
                } else {
                    modelSel.get(i).setSelected(true);
                }
            }
        }

    }
}
