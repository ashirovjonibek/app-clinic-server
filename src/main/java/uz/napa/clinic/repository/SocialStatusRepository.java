package uz.napa.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.napa.clinic.entity.SocialStatus;
import uz.napa.clinic.projection.CustomSocialStatus;

@RepositoryRestResource(path = "socialStatus", excerptProjection = CustomSocialStatus.class)
public interface SocialStatusRepository extends JpaRepository<SocialStatus, Long> {
}
