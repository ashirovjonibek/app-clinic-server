package uz.napa.clinic.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.napa.clinic.entity.Attachment;
import uz.napa.clinic.entity.AttachmentContent;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.repository.AttachmentContentRepository;
import uz.napa.clinic.repository.AttachmentRepository;
import uz.napa.clinic.repository.AttachmentTypeRepository;
import uz.napa.clinic.service.AttachmentService;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentTypeRepository attachmentTypeRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

//    public ApiResponse upload(MultipartFile file) {
//        return new ApiResponse();
//    }

//    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//
//
//        if (!request.getParameter("type").equals("undefined")) {
//            AttachmentType attachmentType = attachmentTypeRepository.findByName(request.getParameter("type"));
//            if (!attachmentType.getContentType().contains(file.getContentType())) {
//                return new ApiResponse("File tipi " + attachmentType.getContentType() + " lardan biri bo'lishi kerak !", false);
//            }
//            Attachment attachment = new Attachment(
//                    file.getName(),
//                    file.getContentType(),
//                    file.getSize()
//            );
//            Attachment savedAttachment = attachmentRepository.save(attachment);
//            try {
//                AttachmentContent attachmentContent = new AttachmentContent(
//                        savedAttachment,
//                        file.getBytes()
//                );
//                attachmentContentRepository.save(attachmentContent);
//                return new ApiResponse("", true, savedAttachment.getId());
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//        }
//        return new ApiResponse("File type noma'lum", false);
//
//
//    }

    public HttpEntity<?> getFile(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getBytes());
    }

    public HttpEntity<?> getVideoFile(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType().substring(0,attachment.getContentType().indexOf(";"))))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getBytes());
    }

    public HttpEntity<?> getAudioFile(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType().replace("*","ogg")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getBytes());
    }


    @Override
    public ApiResponse uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());


//            AttachmentType attachmentType = attachmentTypeRepository.findByName(request.getParameter("type"));
//            if (!attachmentType.getContentType().contains(file.getContentType())) {
//                return new ApiResponse("File tipi " + attachmentType.getContentType() + " lardan biri bo'lishi kerak !", false);
//            }
        Attachment attachment = new Attachment(
                file.getName(),
                file.getContentType(),
                file.getSize()
        );
        Attachment savedAttachment = attachmentRepository.save(attachment);
        try {
            AttachmentContent attachmentContent = new AttachmentContent(
                    savedAttachment,
                    file.getBytes()
            );
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("", true, savedAttachment.getId());


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public HttpEntity<?> getFileById(UUID id) {

        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getBytes());
    }
}
