package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.RedVetMessageEntity;
import com.papps.freddy_lazo.domain.model.RedVetMessage;

import java.util.ArrayList;
import java.util.List;

public class RedVetMessageMapper {


    public static List<RedVetMessage> transform(List<RedVetMessageEntity> schedules) {
        List<RedVetMessage> redVetMessages = new ArrayList<>();
        if (schedules == null)
            return redVetMessages;
        for (RedVetMessageEntity redVetMessageEntity : schedules) {
            redVetMessages.add(transform(redVetMessageEntity));
        }
        return redVetMessages;
    }


    public static RedVetMessage transform(RedVetMessageEntity redVetMessageEntity) {
        return new RedVetMessage(redVetMessageEntity.getId(), redVetMessageEntity.getAppointment_id(), redVetMessageEntity.getMessage(), redVetMessageEntity.getFrom());

    }
}
