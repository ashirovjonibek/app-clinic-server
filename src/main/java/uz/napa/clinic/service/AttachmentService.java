package uz.napa.clinic.service;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.napa.clinic.payload.ApiResponse;

import java.util.UUID;

public interface AttachmentService {

    ApiResponse uploadFile(MultipartHttpServletRequest request);

    HttpEntity<?> getFileById(UUID id);

   HttpEntity<?> getVideoFile(UUID id);

    HttpEntity<?> getAudioFile(UUID id);
}
