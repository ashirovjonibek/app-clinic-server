package uz.napa.clinic.service.iml;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.clinic.entity.Chat;
import uz.napa.clinic.entity.MessageCenter;
import uz.napa.clinic.entity.User;
import uz.napa.clinic.exception.BadRequestException;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.payload.MessageHelper;
import uz.napa.clinic.payload.MessageResponse;
import uz.napa.clinic.payload.UserResponseForMessage;
import uz.napa.clinic.repository.ChatRepository;
import uz.napa.clinic.repository.MessageCenterRepository;
import uz.napa.clinic.repository.UserRepository;
import uz.napa.clinic.service.MessageCenterService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageCenterServiceImpl implements MessageCenterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageCenterRepository messageCenterRepository;

    @Autowired
    ChatRepository chatRepository;


    @Override
    public ApiResponse userMessages(UUID fromId, UUID chatId) {
        List<Chat> chats = chatRepository.findAllByReceiverId(fromId);
        if (chatId == null) {
            List<UserResponseForMessage> response = new ArrayList<>();
            for (int i = 0; i < chats.size(); i++) {
                response.add(
                        UserResponseForMessage.response(chats.get(i).getCreator(),
                                messageCenterRepository.findAllByChatIdAndDeletedFalseAndReadFalse(chats.get(i).getId()).size(),
                                chats.get(i).getId()
                        ));
            }
            return new ApiResponse("Messages", true, response);
        } else {
            List<MessageCenter> messageCenterList = messageCenterRepository.findAllByChatIdAndDeletedFalse(chatId);
            for (MessageCenter message:
                 messageCenterList) {
                if (!message.isRead()){
                    message.setRead(true);
                    messageCenterRepository.save(message);
                }
            }
            return new ApiResponse("Messages", true, messageCenterList.stream().
                    map(messageCenter -> MessageResponse.response(messageCenter)).collect(Collectors.toList())

            );
        }
    }

    @Override
    public ApiResponse sendOrEditMessage(MessageHelper message) {
        MessageCenter messageCenter = new MessageCenter();
        String restMessage;
        int status = 1;
        if (message.getMessageId() != null) {
            messageCenter = messageCenterRepository.findById(message.getMessageId()).orElseThrow(() -> new IllegalStateException("Message not found for edit message!!!"));
            if (messageCenter.getFrom().getId().equals(message.getFromId())) {
                messageCenter.setMessage(message.getMessage());
                messageCenter.setEdit(true);
                messageCenter.setUpdatedAt(new Timestamp(new Date().getTime()));
                messageCenter.setUpdatedBy(messageCenter.getCreatedBy());
                restMessage = "Message successfully edited!!!";
            } else {
                restMessage = "Edit method denied for this user!!!";
                status = 0;
            }
        } else {
            if (message.getChatId() != null) {
                messageCenter.setChat(chatRepository.findById(message.getChatId()).orElseThrow(() -> new IllegalStateException("Chat noot found")));
            } else {
                throw new BadRequestException("Chat not found!!!");
            }
            messageCenter.setEdit(false);
            messageCenter.setCreatedBy(userRepository.findById(message.getFromId()).orElseThrow(() -> new IllegalStateException("User not found for message!!!")));
            messageCenter.setFrom(userRepository.findById(message.getFromId()).orElseThrow(() -> new IllegalStateException("User not found for message!!!")));
            messageCenter.setCreatedAt(new Timestamp(new Date().getTime()));
            messageCenter.setUpdatedAt(new Timestamp(new Date().getTime()));
            messageCenter.setTo(userRepository.findById(message.getToId()).orElseThrow(() -> new IllegalStateException("User not found for message!!!")));
            messageCenter.setMessage(message.getMessage());

            restMessage = "Message saved successfully!!!";
        }

        if (status == 0) {
            return new ApiResponse(restMessage, false);
        } else {

            try {
                messageCenterRepository.save(messageCenter);
                return new ApiResponse(restMessage, true);
            } catch (Exception e) {
                e.printStackTrace();
                return new ApiResponse("Error for saved message!!!", false);
            }
        }
    }

    @Override
    public ApiResponse deleteMessage(UUID fromId, UUID messageId) {

        MessageCenter messageCenter = messageCenterRepository.findById(messageId).orElseThrow(() -> new IllegalStateException("Message not found for delete message!!!"));
        if (messageCenter.getFrom().getId().equals(fromId)) {
            messageCenter.setDeleted(true);
            try {
                messageCenterRepository.save(messageCenter);
                return new ApiResponse("Message deleted!!!", true);
            } catch (Exception e) {
                return new ApiResponse("Error for deleted!!!", false);
            }
        } else {
            return new ApiResponse("Permission denied for this user!!!", false);
        }
    }

    public UUID generateChat(User from, UUID toId) {
        User user = userRepository.findById(toId).orElseThrow(() -> new IllegalStateException("User not found"));
        if (chatRepository.existsByCreatorIdAndReceiverId(from.getId(), user.getId())) {
            return chatRepository.findByCreatorIdAndReceiverId(from.getId(), user.getId()).getId();
        } else
            return chatRepository.save(new Chat(from, user)).getId();

    }
}
