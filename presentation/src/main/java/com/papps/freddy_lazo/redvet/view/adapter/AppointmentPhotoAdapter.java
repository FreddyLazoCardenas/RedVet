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
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentPhotoAdapter extends RecyclerView.Adapter<AppointmentPhotoAdapter.PhotoAppointmentHolder> {

    private List<AppointmentPhotoModel> data = new ArrayList<>();
    private Context context;
    private AppointmentPhotoAdapter.onClickAdapter listener;


    @Inject
    AppointmentPhotoAdapter() {

    }

    @NonNull
    @Override
    public AppointmentPhotoAdapter.PhotoAppointmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_appointment_photo, viewGroup, false);
        context = view.getContext();
        return new PhotoAppointmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentPhotoAdapter.PhotoAppointmentHolder photoAppointmentHolder, int i) {
        photoAppointmentHolder.bind(i);

    }

    public void bindList(List<AppointmentPhotoModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void setListener(BaseActivity listener) {
        this.listener = (onClickAdapter) listener;
    }

    public void setListener(BaseDialogFragment listener) {
        this.listener = (onClickAdapter) listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PhotoAppointmentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        PhotoAppointmentHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            loadImage(data.get(position).getPhoto_url());
        }

        private void loadImage(String photo) {
            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_placeholder)
                    .load(photo != null ? photo : "")
                    .into(ivPhoto);
        }

        @OnClick
        void itemClicked() {
            listener.itemClicked(data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(AppointmentPhotoModel data);
    }
}