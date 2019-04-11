package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.PUT;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private final PetLoverModel petLoverModel;
    private List<PetLoverAppointmentModel> data = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;

    @Inject
    public AppointmentAdapter(PreferencesManager preferencesManager) {
        petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quote, viewGroup, false);
        context = view.getContext();
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void bindList(List<PetLoverAppointmentModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
    }


    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView tvDate;
        @BindView(R.id.txt_time)
        TextView tvTime;
        @BindView(R.id.txt_pet_name)
        TextView tvPetName;
        @BindView(R.id.txt_place)
        TextView tvType;

        AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Calendar calendar = convertToDate(data.get(position).getDate());
            tvDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + " \n" + getMonthForInt(calendar.get(Calendar.MONTH)));
            tvTime.setText(data.get(position).getTime());
            tvPetName.setText(getPetName(data.get(position).getPet_by_pet_lover_id()));
            tvType.setText(data.get(position).getDoctor().getType());
        }

        private Calendar convertToDate(String txt) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                sdf.parse(txt);
                return sdf.getCalendar();
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        private String getMonthForInt(int num) {
            String month = "wrong";
            DateFormatSymbols dfs = new DateFormatSymbols();
            String[] months = dfs.getMonths();
            if (num >= 0 && num <= 11) {
                month = months[num];
            }
            return month;
        }

        private String getPetName(String pet_by_pet_lover_id) {
            for (PetLoverRegisterModel petLoverRegisterModel : petLoverModel.getPetList()) {
                if (petLoverRegisterModel.getId() == Integer.valueOf(pet_by_pet_lover_id)) {
                    return petLoverRegisterModel.getName();
                }
            }
            return "";
        }

        @OnClick
        public void itemClicked(){
            listener.itemClicked(data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(PetLoverAppointmentModel data);
    }
}
