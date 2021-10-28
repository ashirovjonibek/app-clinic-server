package uz.napa.clinic.service;

import uz.napa.clinic.entity.MessageCenter;
import uz.napa.clinic.entity.User;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.payload.MessageHelper;
import uz.napa.clinic.payload.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface MessageCenterService {

    ApiResponse userMessages(User from,UUID toId);

    ApiResponse sendOrEditMessage(MessageHelper message);

    ApiResponse deleteMessage(UUID fromId,UUID messageId);

    UUID generateChat(User from, UUID toId);

    List<MessageResponse> findAll();
}
