package uz.napa.clinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.napa.clinic.entity.*;
import uz.napa.clinic.entity.enums.AnswerStatus;
import uz.napa.clinic.entity.enums.ApplicationStatus;
import uz.napa.clinic.entity.enums.DocumentStatus;
import uz.napa.clinic.projection.CustomInfoCount;

import javax.print.attribute.standard.DocumentName;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findByStatusAndDeletedFalse(DocumentStatus status);

    List<Document> findByStatusAndAnswerIsNullAndDeletedFalse(DocumentStatus status);

    Page<Document> findByStatusAndAnswerIsNullAndDeletedFalse(DocumentStatus status, Pageable pageable);

//    @Query(value = "select *from document where section_id=?2 and status=?1",nativeQuery = true)
    Page<Document> findByStatusAndSectionAndAnswerIsNull(DocumentStatus status,Section section, Pageable pageable);

    Page<Document> findByAnswerInAndDeletedFalseAndCheckedBy(List<Answer> answer, Pageable pageable, User user);

    Page<Document> findByCheckedByAndStatusAndDeletedFalseAndAnswerIsNull(User user, DocumentStatus status, Pageable pageable);

    Page<Document> findByStatusInAndDeletedFalse(List<DocumentStatus> status, Pageable pageable);

    Page<Document> findByStatusInAndDeletedFalseAndCheckedBySection(Collection<DocumentStatus> status, Section checkedBy_section, Pageable pageable);

    Document findByApplicationAndDeletedFalseAndStatus(Application application, DocumentStatus status);

    Optional<Document> findByAnswer(Answer answer);

    Page<Document> findAllByAnswerIn(Collection<Answer> answer, Pageable pageable);

    Page<Document> findByStatusAndDeletedFalseAndCheckedBySection(DocumentStatus status, Section checkedBy_section, Pageable pageable);

    List<Document> findAllByAnswerIn(Collection<Answer> answer);

    Page<Document> findByCheckedByAndStatusAndDeletedFalseAndAnswerIsNotNull(User user, DocumentStatus status,Pageable pageable);

    Page<Document> findByCheckedByAndStatusAndDeletedFalse(User checkedBy, DocumentStatus status, Pageable pageable);

    Page<Document> findByApplicationInAndStatusAndDeletedFalseAndAnswerStatus(List<Application> application, DocumentStatus status, AnswerStatus answer_status, Pageable pageable);

    @Query(nativeQuery = true, value = "select u,COUNT(d.status),d.status from document d\n" +
            "left join users u\n" +
            "on d.checked_by=u.id\n" +
            "left join application a\n" +
            "on d.application_id=a.id\n" +
            "where d.checked_by is not null and d.status='CREATED' or d.status='COMPLETED'\n" +
            "group by (d.status,u,d.status) ")
    List<CustomInfoCount> getDocumentByCheckedBy();
}
