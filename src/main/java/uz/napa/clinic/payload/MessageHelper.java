package uz.napa.clinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHelper {

    private UUID messageId;

    private UUID fromId;

    private UUID toId;

    private String message;

    private UUID chatId;
}
