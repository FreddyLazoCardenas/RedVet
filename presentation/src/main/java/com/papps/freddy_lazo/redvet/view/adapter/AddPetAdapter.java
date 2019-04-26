package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPetAdapter extends RecyclerView.Adapter<AddPetAdapter.PetViewHolder> {


    private List<PetRedVetModel> data = new ArrayList<>();
    private onClickAdapter listener;
    private Context context;

    @Inject
    AddPetAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_pet, viewGroup, false);
        context = view.getContext();
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<PetRedVetModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
    }


    class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pet)
        ImageView imgPet;


        PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            loadImage(data.get(position).getPhoto_url());
            if (data.get(position).isSelected()) {
                imgPet.setAlpha(1f);
            } else {
                imgPet.setAlpha(0.3f);
            }
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

        private void loadImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_cat)
                    .load(photo != null ? photo : "")
                    .into(imgPet);
        }


        @OnClick
        void itemClick() {
            itemSelected(getAdapterPosition());
            bindList(data);
            listener.data(data);
        }
    }

    public interface onClickAdapter {
        void data(List<PetRedVetModel> data);
    }
}
