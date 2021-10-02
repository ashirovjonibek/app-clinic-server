package uz.napa.clinic.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.repository.AttachmentTypeRepository;
import uz.napa.clinic.service.iml.AttachmentServiceImpl;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/attach")
public class AttachmentController {
    private static final String CREATE = "/upload";
    private static final String GET_BY_ID = "/{id}";
    private static final String GET_BY_ID_VIDEO = "/video/{id}";
    private static final String GET_BY_ID_AUDIO = "/audio/{id}";
    final
    AttachmentServiceImpl attachmentService;
    final
    AttachmentTypeRepository attachmentTypeRepository;

    public AttachmentController(AttachmentServiceImpl attachmentService, AttachmentTypeRepository attachmentTypeRepository) {
        this.attachmentService = attachmentService;
        this.attachmentTypeRepository = attachmentTypeRepository;
    }

    @PostMapping(CREATE)
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest file) throws IOException {
        ApiResponse response = attachmentService.uploadFile(file);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }


    @GetMapping(GET_BY_ID)
    public HttpEntity<?> getFileById(@PathVariable UUID id) {
        return attachmentService.getFileById(id);
    }

    @GetMapping(GET_BY_ID_VIDEO)
    public HttpEntity<?> getVideoFileById(@PathVariable UUID id) {
        return attachmentService.getVideoFile(id);
    }

    @GetMapping(GET_BY_ID_AUDIO)
    public HttpEntity<?> getAudioFileById(@PathVariable UUID id) {
        return attachmentService.getAudioFile(id);
    }

//    @GetMapping("/type")
//    public AttachmentType getName(@RequestParam(name = "type") String typeName) {
//        return attachmentTypeRepository.findByName(typeName);
//    }
}
