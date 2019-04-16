package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.NotificationEntity;
import com.papps.freddy_lazo.data.local.database.NotificationTable;

import java.util.ArrayList;
import java.util.List;

public class NotificationTableDataMapper {

    public NotificationTableDataMapper() {
    }

    public static NotificationTable transform(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }
        return new NotificationTable(entity.getType(), entity.getAppointment_id(),entity.getMessage(),entity.getTime(),entity.isFrom_doctor());
    }

    public static List<NotificationTable> transformList(List<NotificationEntity> entities) {
        List<NotificationTable> storeTables = new ArrayList<>();
        for (NotificationEntity entity : entities) {
            storeTables.add(NotificationTableDataMapper.transform(entity));
        }
        return storeTables;
    }

    public static NotificationEntity transform(NotificationTable table) {
        if (table == null) return null;
        return new NotificationEntity(table.getType(),
                table.getAppointment_id(),table.getMessage(),table.getTime(),table.isFrom_doctor());
    }

    public static List<NotificationEntity> transform(List<NotificationTable> tables) {
        List<NotificationEntity> entities = new ArrayList<>();
        for (NotificationTable table : tables) {
            entities.add(transform(table));
        }
        return entities;
    }
}
