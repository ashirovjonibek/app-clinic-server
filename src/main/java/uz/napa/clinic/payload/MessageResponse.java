package uz.napa.clinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.napa.clinic.entity.MessageCenter;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private UUID messageId;

    private UUID fromId;

    private UUID toId;

    private String message;

    private UUID chatId;

    private boolean edit;

    private Timestamp createdAt;


    public static MessageResponse response(MessageCenter messageCenter){
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setCreatedAt(messageCenter.getCreatedAt());
        messageResponse.setEdit(messageCenter.isEdit());
        messageResponse.setFromId(messageCenter.getCreatedBy().getId());
        messageResponse.setMessage(messageCenter.getMessage());
        messageResponse.setToId(messageCenter.getTo().getId());
        messageResponse.setMessageId(messageCenter.getId());
        messageResponse.setChatId(messageCenter.getChat().getId());
        return messageResponse;
    }
}
