package uz.napa.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.clinic.entity.DelayedApplications;

import java.util.UUID;

public interface DelayedApplicationsRepository extends JpaRepository<DelayedApplications, UUID> {
}
