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
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.PetModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetViewHolder> {


    private List<PetLoverRegisterModel> data = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;

    @Inject
    PetProfileAdapter() {
        //empty constructor
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pet_profile, viewGroup, false);
        context = viewGroup.getContext();
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


    public void bindList(List<PetLoverRegisterModel> data) {
        this.data = data;
    }

    public void setListener(BaseFragment baseFragment) {
        listener = (onClickAdapter) baseFragment;
    }

    public void removePet(PetLoverRegisterModel petLoverRegisterModel) {
        int index = data.indexOf(petLoverRegisterModel);
        data.remove(petLoverRegisterModel);
        notifyItemRemoved(index);
    }

    public void updateData(PetLoverRegisterModel model) {
        int index = getItemIndex(model);
        data.set(index, model);
        notifyItemChanged(index);
    }

    private int getItemIndex(PetLoverRegisterModel model){
        for (PetLoverRegisterModel petLoverRegisterModel : data){
            if(petLoverRegisterModel.getId() == model.getId()){
                return data.indexOf(petLoverRegisterModel);
            }
        }
        return -1;
    }


    class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pet)
        ImageView imgPet;
        @BindView(R.id.tv_pet_name)
        TextView tvPetName;
        @BindView(R.id.tv_pet_birthday)
        TextView tvPetBirthday;
        @BindView(R.id.tv_breed)
        TextView tvBreed;


        PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            tvPetName.setText(data.get(position).getName());
            tvPetBirthday.setText(data.get(position).getBirthday());
            tvBreed.setText(data.get(position).getBreed());
            loadImage(data.get(position).getPhoto_url());
        }

        private void loadImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_placeholder)
                    .load(photo != null ? photo : "")
                    .into(imgPet);
        }

        @OnClick
        void itemClick() {
            listener.itemClicked(data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(PetLoverRegisterModel data);
    }

}
