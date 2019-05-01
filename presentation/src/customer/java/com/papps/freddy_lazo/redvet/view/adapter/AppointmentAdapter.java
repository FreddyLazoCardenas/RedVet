package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private final PetLoverModel petLoverModel;
    private List<PetLoverAppointmentModel> data = new ArrayList<>();
    private List<PetLoverAppointmentModel> filterData = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;
    private boolean isFiltering;

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
        return isFiltering ? filterData.size() : data.size();
    }

    public void bindList(List<PetLoverAppointmentModel> data) {
        if (data != null)
            this.data = data;
        changeVisibility(data);
        notifyDataSetChanged();
    }

    private void changeVisibility(List<PetLoverAppointmentModel> data) {
        if(data!= null && data.isEmpty()){
            listener.emptyList();
        }else{
            listener.notEmptyList();
        }
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
    }

    public void setFiltering(boolean isFiltering) {
        this.isFiltering = isFiltering;
    }

    public boolean isFiltering() {
        return isFiltering;
    }

    public void bindFilterList(String appointmentStatus) {
        filterData.clear();
        for (PetLoverAppointmentModel model : data) {
            if (model.getStatus().equals(appointmentStatus)) {
                filterData.add(model);
            }
        }
        changeVisibility(filterData);
        notifyDataSetChanged();
    }

    public void removeAppointment(int id) {
        int index = getItemIndex(id);
        data.remove(index);
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            changeVisibility(filterData);
            notifyItemRemoved(filteringIndex);
        } else {
            changeVisibility(data);
            notifyItemRemoved(index);
        }
    }

    private int getFilterItemIndex(int id) {
        for (PetLoverAppointmentModel model : filterData) {
            if (model.getId() == id) {
                return filterData.indexOf(model);
            }
        }
        return 0;
    }

    private int getItemIndex(int id) {
        for (PetLoverAppointmentModel model : data) {
            if (model.getId() == id) {
                return data.indexOf(model);
            }
        }
        return 0;
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
            PetLoverAppointmentModel bindData = isFiltering ? filterData.get(position) : data.get(position);
            Calendar calendar = convertToDate(bindData.getDate());
            tvDate.setText(MessageFormat.format("{0} \n{1}", calendar.get(Calendar.DAY_OF_MONTH), getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3)));
            tvTime.setText(bindData.getTime());
            tvPetName.setText(getPetName(bindData.getPet_by_pet_lover_id()));
            tvType.setText(setJobText(bindData.getDoctor().getType()));
            tvType.setCompoundDrawablesWithIntrinsicBounds(getIcon(bindData.getDoctor().getType()), 0, 0, 0);

        }

        private int getIcon(String type) {
            switch (type) {
                case "clinic":
                    return R.drawable.ic_hospital;
                case "vet":
                    return R.drawable.ic_hospital;
                case "other":
                    return R.drawable.ic_house;
                default:
                    return R.string.doctor_clinic_type;
            }
        }

        private int setJobText(String type) {
            switch (type) {
                case "clinic":
                    return R.string.doctor_clinic_type;
                case "vet":
                    return R.string.doctor_vet_type;
                case "other":
                    return R.string.doctor_other_type;
                default:
                    return R.string.doctor_clinic_type;
            }
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
        public void itemClicked() {
            listener.itemClicked(isFiltering ? filterData.get(getAdapterPosition()) : data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(PetLoverAppointmentModel data);

        void emptyList();

        void notEmptyList();
    }
}
