package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;

import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetViewHolder> {


    private List<PetLoverRegisterModel> data = new ArrayList<>();
    private List<PetRedVetModel> petData = new ArrayList<>();
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

    public void addData(PetLoverRegisterModel model) {
        data.add(model);
        notifyItemInserted(data.indexOf(model));
    }

    private int getItemIndex(PetLoverRegisterModel model) {
        for (PetLoverRegisterModel petLoverRegisterModel : data) {
            if (petLoverRegisterModel.getId().equals(model.getId())) {
                return data.indexOf(petLoverRegisterModel);
            }
        }
        return -1;
    }

    public List<PetLoverRegisterModel> getData() {
        return data;
    }

    public void setPetRedVetModel(List<PetRedVetModel> petData) {
        this.petData = petData;
        notifyDataSetChanged();
    }

    class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pet)
        ImageView imgPet;
        @BindView(R.id.iv_pet)
        ImageView ivPet;
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
            loadPetImageLogic(data.get(position).getPet_id());
            Calendar calendar = DateHelper.convertToDate(data.get(position).getBirthday());
            tvPetBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)).replaceAll(",", ""));
            tvBreed.setText(context.getString(R.string.pet_breed, data.get(position).getBreed() != null ? data.get(position).getBreed() : ""));
            loadImage(data.get(position).getPhoto_url());
        }

        private void loadPetImageLogic(int pet_id) {
            for (PetRedVetModel dataPet : petData) {
                if (dataPet.getId() == pet_id) {
                    loadPetImage(dataPet.getPhoto_url());
                    break;
                }
            }
        }

        private void loadImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_placeholder)
                    .load(photo != null ? photo : "")
                    .into(imgPet);
        }

        private void loadPetImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_cat)
                    .load(photo != null ? photo : "")
                    .into(ivPet);
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
