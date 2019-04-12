package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.RedVetMessageEntity;

import java.util.List;

public class ChatRedVetResponse {

    @SerializedName("message")
    private RedVetMessageEntity message;

    @SerializedName("messages")
    private List<RedVetMessageEntity> messages;

    public RedVetMessageEntity getMessage() {
        return message;
    }

    public List<RedVetMessageEntity> getMessages() {
        return messages;
    }
}
