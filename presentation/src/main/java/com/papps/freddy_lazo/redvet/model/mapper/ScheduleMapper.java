package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.data.entity.ScheduleEntity;
import com.papps.freddy_lazo.domain.model.Schedule;
import com.papps.freddy_lazo.redvet.model.ScheduleModel;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {

    public ScheduleMapper() {
    }

    public static List<ScheduleModel> transform(List<Schedule> entity) {
        List<ScheduleModel> schedules = new ArrayList<>();
        if (entity == null) {
            return schedules;
        }
        for (Schedule scheduleEntity : entity){
            schedules.add(transform(scheduleEntity));
        }
        return schedules;
    }

    private static ScheduleModel transform(Schedule entity){
        return new ScheduleModel(entity.getId(),entity.getDay(),entity.getStart_time(),entity.getEnd_time());
    }
}
