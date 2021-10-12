package uz.napa.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.napa.clinic.entity.Attachment;
import uz.napa.clinic.entity.enums.AttachStatus;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Page<Attachment> findAllByStatusAndDeletedFalse(AttachStatus status,Pageable pageable);
}
