package uz.napa.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.clinic.entity.MessageCenter;
import uz.napa.clinic.entity.User;

import java.util.List;
import java.util.UUID;

public interface MessageCenterRepository extends JpaRepository<MessageCenter, UUID> {
    List<MessageCenter> findAllByCreatedByIdAndToIdAndDeletedFalseOrderByCreatedAtAsc(UUID fromId,UUID toId);

    List<MessageCenter> findAllByCreatedByIdAndToIdAndDeletedFalseAndReadFalseOrderByCreatedAtAsc(UUID fromId,UUID toId);

    List<MessageCenter> findAllByToIdAndDeletedFalseAndReadFalseOrderByCreatedAt(UUID toId);

    List<MessageCenter> findAllByChatIdAndDeletedFalseAndReadFalse(UUID chatId);

    List<MessageCenter> findAllByChatIdAndDeletedFalse(UUID chatId);
}
