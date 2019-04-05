package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.ScheduleEntity;
import com.papps.freddy_lazo.domain.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {

    public static List<Schedule> transform(List<ScheduleEntity> schedules) {
        List<Schedule> scheduleList = new ArrayList<>();
        if (schedules == null)
            return scheduleList;
        for (ScheduleEntity scheduleEntity : schedules) {
            scheduleList.add(transform(scheduleEntity));
        }
        return scheduleList;    }


    public static Schedule transform(ScheduleEntity scheduleEntity) {
        return new Schedule(scheduleEntity.getId(),scheduleEntity.getDay(),scheduleEntity.getStart_time(),scheduleEntity.getEnd_time());

    }

}
