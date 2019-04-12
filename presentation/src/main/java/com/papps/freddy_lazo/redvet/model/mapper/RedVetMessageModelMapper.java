package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.redvet.model.RedVetMessageModel;

import java.util.ArrayList;
import java.util.List;

public class RedVetMessageModelMapper {

    public static List<RedVetMessageModel> transform(List<RedVetMessage> schedules) {
        List<RedVetMessageModel> redVetMessageModels = new ArrayList<>();
        if (schedules == null)
            return redVetMessageModels;
        for (RedVetMessage redVetMessage : schedules) {
            redVetMessageModels.add(transform(redVetMessage));
        }
        return redVetMessageModels;
    }


    public static RedVetMessageModel transform(RedVetMessage redVetMessage) {
        return new RedVetMessageModel(redVetMessage.getId(), redVetMessage.getAppointment_id(), redVetMessage.getMessage(), redVetMessage.getFrom());

    }
}
