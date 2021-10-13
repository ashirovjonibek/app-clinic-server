package uz.napa.clinic.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import uz.napa.clinic.entity.User;
import uz.napa.clinic.entity.enums.AttachStatus;
import uz.napa.clinic.entity.enums.UserStatus;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.payload.AttachResponse;
import uz.napa.clinic.payload.ResPageable;
import uz.napa.clinic.repository.AttachmentContentRepository;
import uz.napa.clinic.repository.AttachmentRepository;
import uz.napa.clinic.repository.AttachmentTypeRepository;
import uz.napa.clinic.service.AttachmentService;
import uz.napa.clinic.utils.CommonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentTypeRepository attachmentTypeRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

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

    public HttpEntity<?> getVideoFile(UUID id) throws MalformedURLException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .contentLength(attachment.getSize())
                .body(new FileUrlResource(attachment.getUploadPath()));
    }

    public HttpEntity<?> getAudioFile(UUID id) throws MalformedURLException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);


        return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType().replace("*","ogg")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .contentLength(attachment.getSize())
                .body(new FileUrlResource(attachment.getUploadPath()));
    }

    @Override
    public ResPageable getNormativeLegalBase(int page,int size) {
        Pageable pageable = CommonUtils.getPageable(page, size);
        Page<Attachment> attachments = attachmentRepository.findAllByStatusAndDeletedFalse(AttachStatus.FOR_NORMATIVE_LEGAL_BASE, pageable);
        return new ResPageable(
                attachments.getContent().stream().map(attachment -> AttachResponse.toFront(attachment)).collect(Collectors.toList()),
                page,
                attachments.getTotalPages(),
                attachments.getTotalElements()
        );

    }


    @Override
    public ApiResponse uploadFile(MultipartHttpServletRequest request, User user) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Date date=new Date();
        File folder=new File(String.format("%s/%d/%d/%d",uploadFolder,1900+date.getYear(),1+date.getMonth(),date.getDate()));
        if (!folder.exists()&&folder.mkdirs()){
            System.out.println("folder created!!!");
        }

        Attachment attachment = new Attachment(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                getExt(file.getOriginalFilename()),
                uploadFolder,
                !user.getStatus().equals(UserStatus.ADMIN)?
                        AttachStatus.FROM_THE_APPLICANT:
                        AttachStatus.FOR_NORMATIVE_LEGAL_BASE
        );
        attachment.setCreatedBy(user);
        attachment.setUpdatedBy(user);
        attachment.setCreatedAt(new Timestamp(new Date().getTime()));
        attachment.setUpdatedAt(new Timestamp(new Date().getTime()));
        Attachment savedAttachment = attachmentRepository.save(attachment);
        savedAttachment.setUploadPath(String.format("%d/%d/%d/%s.%s",1900+date.getYear(),1+date.getMonth(),date.getDate(),
                savedAttachment.getId(),
                savedAttachment.getFileExtension()
                ));
        attachmentRepository.save(savedAttachment);
        folder=folder.getAbsoluteFile();
        File file1=new File(folder,String.format("%s.%s",savedAttachment.getId(),savedAttachment.getFileExtension()));
        try {
            file.transferTo(file1);
            return new ApiResponse("File saved!!!", true, savedAttachment.getId());
        }catch (IOException e){
            e.printStackTrace();
            return new ApiResponse("Error for saved!!!", true, savedAttachment.getId());
        }
//        try {
//            AttachmentContent attachmentContent = new AttachmentContent(
//                    savedAttachment,
//                    file.getBytes()
//            );
//            attachmentContentRepository.save(attachmentContent);
//
//
//            return new ApiResponse("", true, savedAttachment.getId());
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }


    }

    @Override
    public HttpEntity<?> getFileById(UUID id) throws IOException {

        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
//        File file=new File(String.format(attachment.getUploadPath()));
//
//        InputStreamResource inputStreamResource=new InputStreamResource(new FileInputStream(file.getAbsoluteFile()));
////
////
//            return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
//                .header(HttpHeaders.CONTENT_TYPE, attachment.getContentType())
//                .contentLength(attachment.getSize())
//                .body(inputStreamResource);

//        Path path = Paths.get(attachment.getUploadPath());
//        Resource resource = null;
//        try {
//            resource = new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(attachment.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
        System.out.println(String.format("%s/%s",uploadFolder,attachment.getUploadPath()));
        FileUrlResource fileUrlResource=null;
        try {
            fileUrlResource = new FileUrlResource(String.format("%s/%s",uploadFolder,attachment.getUploadPath()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .contentLength(attachment.getSize())
                .body(fileUrlResource);
    }
    private String getExt(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    public ApiResponse delete(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new IllegalStateException("File not found for delete id!!!"));
        attachment.setDeleted(true);
        Attachment save = attachmentRepository.save(attachment);
        return new ApiResponse(save.isDeleted()?"File deleted!!!":"Error for deleted!!!",save.isDeleted());
    }
}
