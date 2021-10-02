package uz.napa.clinic.service;

import uz.napa.clinic.entity.Application;
import uz.napa.clinic.entity.User;
import uz.napa.clinic.payload.*;
import uz.napa.clinic.projection.*;
import uz.napa.clinic.repository.CustomInfoStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ApplicationService {
    ApiResponse create(ApplicationRequest request);

    ApplicationResponse getOne(UUID id);

    ApiResponse update(UUID id, ApplicationRequest request);

    ApiResponse delete(UUID id);

    List<ApplicationResponse> list();

    ResPageable getMyApplications(int page, int size, User user);

    ResPageable getAllUnCheckedByListener(int page, int size, User user);

    ResPageable getAllCheckedByListener(int page, int size, User user);

    ApiResponse acceptedApplicationByListener(UUID id, User user);

    ApiResponse ignoredApplicationByListener(UUID id,Commit message, User user);

    List<ApplicationResponse> topList();

    ResPageable listByListener(User user, int page, int size);

    List<CustomInfoRegion> getByRegion();

    List<CustomInfoRegion> getByDenied();

    List<CustomAge> getByAge();

    List<CustomGender> getByGender();

    List<CustomInfoStatus> getByStatus();

    List<SectionStatusCount> getBySection();

    List<CustomInfoSocialStatus> getBySocialStatus();

    List<CustomInfoYear> getByYear();

    List<ListenerStatusCount> getInfoListener();

    List<CustomUserInfo> getInfoApplicant();
}
