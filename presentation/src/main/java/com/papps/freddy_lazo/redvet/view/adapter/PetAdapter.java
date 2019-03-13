package com.papps.freddy_lazo.redvet.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.papps.freddy_lazo.redvet.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    @Inject
    PetAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_pet, viewGroup, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void bindList() {
        notifyDataSetChanged();
    }

    class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pet)
        ImageView imgPet;


        PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            imgPet.setImageResource(getImage(position));
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
