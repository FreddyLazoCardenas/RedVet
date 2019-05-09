package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private final String type;
    private List<DoctorAppointmentModel> data = new ArrayList<>();
    private List<DoctorAppointmentModel> filterData = new ArrayList<>();
    private Context context;
    private onClickAdapter listener;
    private boolean isFiltering;

    @Inject
    public AppointmentAdapter(PreferencesManager preferencesManager) {
        type = DoctorModel.toModel(preferencesManager.getDoctorCurrentUser()).getType();
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

    public void bindList(List<DoctorAppointmentModel> data) {
        if (data != null)
            this.data = data;
        changeVisibility(data);
        notifyDataSetChanged();
    }

    private void changeVisibility(List<DoctorAppointmentModel> data) {
        if (data != null && data.isEmpty()) {
            listener.emptyList();
        } else {
            listener.notEmptyList();
        }
    }

    public void setFiltering(boolean isFiltering) {
        this.isFiltering = isFiltering;
    }

    public boolean isFiltering() {
        return isFiltering;
    }

    public void bindFilterList(String appointmentStatus) {
        filterData.clear();
        for (DoctorAppointmentModel model : data) {
            if (model.getStatus().equals(appointmentStatus)) {
                filterData.add(model);
            }
        }
        changeVisibility(filterData);
        notifyDataSetChanged();
    }

    public void setView(BaseFragment fragment) {
        listener = (onClickAdapter) fragment;
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

    public void removeFinishedAppointment(int id) {
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            changeVisibility(filterData);
            notifyItemRemoved(filteringIndex);
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

    private int getFilterItemIndex(int id) {
        for (DoctorAppointmentModel model : filterData) {
            if (model.getId() == id) {
                return filterData.indexOf(model);
            }
        }
        return 0;
    }

    private int getItemIndex(int id) {
        for (DoctorAppointmentModel model : data) {
            if (model.getId() == id) {
                return data.indexOf(model);
            }
        }
        return 0;
    }

    public void doctorPendingAppointment(int id) {
        int index = getItemIndex(id);
        DoctorAppointmentModel model = data.get(index);
        model.setStatus("confirmed");
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            notifyItemRemoved(filteringIndex);
        } else {
            notifyItemChanged(index);
        }
    }

    public void doctorConfirmAppointment(int id) {
        int index = getItemIndex(id);
        DoctorAppointmentModel model = data.get(index);
        model.setStatus("finished");
        if (isFiltering) {
            int filteringIndex = getFilterItemIndex(id);
            filterData.remove(filteringIndex);
            notifyItemRemoved(filteringIndex);
        } else {
            notifyItemChanged(index);
        }
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_month)
        TextView tvMonth;
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
            itemView.setBackgroundColor(ContextCompat.getColor(context, position % 2 == 0 ? R.color.colorWhite : R.color.colorNotificationGray));
            DoctorAppointmentModel bindData = isFiltering ? filterData.get(position) : data.get(position);
            Calendar calendar = convertToDate(bindData.getDate());
            tvDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            tvMonth.setText(getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3));
            tvTime.setText(bindData.getTime());
            tvPetName.setText(bindData.getPet().getName());
            tvType.setText(setJobText(type));
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

        @OnLongClick
        boolean itemLongClick(){
            listener.itemLongClicked(isFiltering ? filterData.get(getAdapterPosition()) : data.get(getAdapterPosition()));
            return true;
        }

        @OnClick
        void itemClicked() {
            listener.itemClicked(isFiltering ? filterData.get(getAdapterPosition()) : data.get(getAdapterPosition()));
        }
    }

    public interface onClickAdapter {
        void itemClicked(DoctorAppointmentModel data);

        void emptyList();

        void notEmptyList();

        void itemLongClicked(DoctorAppointmentModel doctorAppointmentModel);
    }
}
