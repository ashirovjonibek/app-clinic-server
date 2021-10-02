package uz.napa.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import uz.napa.clinic.entity.District;
import uz.napa.clinic.projection.CustomDistrict;

import java.util.List;

@Repository
@RepositoryRestResource(path = "district", excerptProjection = CustomDistrict.class)
public interface DistrictRepository extends JpaRepository<District, Long> {
    @RestResource(path = "/filterByRegion")
    List<District> findAllByRegionId(@Param("id") Long id);
}
