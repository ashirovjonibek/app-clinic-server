package uz.napa.clinic.service.iml;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.napa.clinic.entity.*;
import uz.napa.clinic.entity.enums.AnswerStatus;
import uz.napa.clinic.entity.enums.ApplicationStatus;
import uz.napa.clinic.entity.enums.DocumentStatus;
import uz.napa.clinic.entity.enums.UserStatus;
import uz.napa.clinic.exception.BadRequestException;
import uz.napa.clinic.payload.*;
import uz.napa.clinic.projection.CustomInfoCount;
import uz.napa.clinic.repository.*;
import uz.napa.clinic.service.DocumentService;
import uz.napa.clinic.utils.CommonUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final ApplicationRepository applicationRepository;
    private final AnswerServiceImpl answerService;
    private final AnswerRepository answerRepository;
    private final SectionRepository sectionRepository;

    public DocumentServiceImpl(UserRepository userRepository, DocumentRepository documentRepository, ApplicationRepository applicationRepository, AnswerServiceImpl answerService, AnswerRepository answerRepository, SectionRepository sectionRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.applicationRepository = applicationRepository;
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public ApiResponse create(ApplicationRequest request) {
        return null;
    }

    @Override
    public ApplicationResponse getOne(UUID id) {
        return null;
    }

    @Override
    public ApiResponse update(UUID id, ApplicationRequest request) {
        return null;
    }

    @Override
    public ApiResponse delete(UUID id) {
        return null;
    }

    @Override
    public List<ApplicationResponse> list() {
        return null;
    }

    @Override
    public ApiResponse changeListener(UUID documentId, UUID userId) {
        Optional<User> findUser = userRepository.findById(userId);
        Optional<Document> findDocument = documentRepository.findById(documentId);
        if (findUser.isPresent()) {
            if (findDocument.isPresent()) {
                Document document = findDocument.get();
                document.setStatus(DocumentStatus.TRASH);
                Application application = document.getApplication();
                document.getApplication().setDeadline(null);
                Document newDocument = new Document();
                newDocument.setApplication(application);
                newDocument.setCheckedBy(findUser.get());
                newDocument.setStatus(DocumentStatus.CREATED);
                documentRepository.save(document);
                documentRepository.save(newDocument);
                return new ApiResponse("Application checker is changed ", true);
            } else {
                throw new BadRequestException("Application not found with ID: " + documentId);
            }
        } else {
            throw new BadRequestException("User not found with ID: " + userId);
        }
    }

    @Override
    public DocumentResponse getByApplication(UUID id) {
        Optional<Application> findApplication = applicationRepository.findById(id);
        if (findApplication.isPresent()) {
            Document findDocument = documentRepository.findByApplicationAndDeletedFalseAndStatus(findApplication.get(), DocumentStatus.DENIED);
            return DocumentResponse.fromEntity(findDocument);

        } else {
            throw new BadRequestException("Application not found with ID: " + id);
        }
    }

    @Override
    public ResPageable getBossAnswers(User user, int page, int size) {
        Pageable pageable = CommonUtils.getPageable(page, size);
        Page<Document> allByAnswerIn = documentRepository.findByStatusAndDeletedFalseAndCheckedBySectionOrderByCreatedAtDesc(DocumentStatus.WAITING, user.getSection(), pageable);
        return new ResPageable(
                allByAnswerIn.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                page,
                allByAnswerIn.getTotalPages(),
                allByAnswerIn.getTotalElements()
        );
    }

    @Override
    public ApiResponse confirmByBoss(UUID id) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            Document document = byId.get();
            Answer answer = document.getAnswer();
            Application application = document.getApplication();
            document.setStatus(DocumentStatus.COMPLETED);
            answer.setStatus(AnswerStatus.ACCEPTED);
            application.setStatus(ApplicationStatus.COMPLETED);
            documentRepository.save(document);
            answerRepository.save(answer);
            applicationRepository.save(application);
            return new ApiResponse("Confirmed", true);
        } else {
            throw new BadRequestException("Document not found by ID: " + id);
        }
    }

    @Override
    public ResPageable getAllCheckedByListener(int page, int size, User user) {
        Pageable pageable = CommonUtils.getPageable(page, size);
        Page<Document> checkedByListener = documentRepository.findByCheckedByAndStatusAndDeletedFalseAndAnswerIsNullOrderByCreatedAtDesc(user, DocumentStatus.COMPLETED, pageable);
        List<ApplicationResponse> applications = checkedByListener.getContent().stream().map(document -> ApplicationResponse.fromEntity(document.getApplication())).collect(Collectors.toList());
        return new ResPageable(
                applications,
                page,
                checkedByListener.getTotalPages(),
                checkedByListener.getTotalElements()
        );
    }

    @Override
    public ApiResponse denied(UUID id, String comment) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            Document document = byId.get();
            document.setStatus(DocumentStatus.DENIED);
            Answer answer = document.getAnswer();
            answer.setStatus(AnswerStatus.DENIED);
            if (comment != null) {
                answer.setDeniedMessage(comment);
            }
            answerRepository.save(answer);
            documentRepository.save(document);
            return new ApiResponse("Document send to Listener", true);
        } else {
            throw new BadRequestException("Document not found with ID: " + id);
        }
    }

    @Override
    public ResPageable deniedAnswerDocument(User user,Pageable pageable) {
        Page<Document> documents = documentRepository.findByCheckedByAndStatusAndDeletedFalseAndAnswerIsNotNullOrderByCreatedAtDesc(user, DocumentStatus.DENIED, pageable);
        return new ResPageable(
                documents.stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                pageable.getPageNumber(),
                documents.getTotalPages(),
                documents.getTotalElements()
        );
    }

    @Override
    public ResPageable getCheckedApplication(int page, int size, User user) {
        Pageable pageable = CommonUtils.getPageable(page, size);
        List<Application> applications = applicationRepository.findByCreatedByAndDeletedFalse(user);
        Page<Document> documents = documentRepository.findByApplicationInAndStatusAndDeletedFalseAndAnswerStatusOrderByCreatedAtDesc(applications, DocumentStatus.COMPLETED, AnswerStatus.COMPLETED, pageable);
        return new ResPageable(
                documents.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                page,
                documents.getTotalPages(),
                documents.getTotalElements()
        );
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public ResPageable findAllByPageable(int page, int size,DocumentStatus status,User user) {
        Pageable pageable = CommonUtils.getPageable(page, size);
        Page<Document> documentPage=null;

        if (status.equals(DocumentStatus.ALL)){
            documentPage=documentRepository.findAll(pageable);
        }else {
            if (status.equals(DocumentStatus.FORWARD_TO_MODERATOR))documentPage=documentRepository.findByStatusAndSectionAndDeletedFalseOrderByCreatedAtDesc(status,user.getSection(),pageable);
            else documentPage=documentRepository.findByStatusAndDeletedFalseOrderByCreatedAtDesc(status,pageable);
        }

        return new ResPageable(
                documentPage.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                page,
                documentPage.getTotalPages(),
                documentPage.getTotalElements()
        );
    }

    @Override
    public ResPageable getAlllAcceptedDocument(Pageable pageable) {
        List<DocumentStatus> statusList = new ArrayList<>(Arrays.asList(DocumentStatus.CREATED, DocumentStatus.INPROCESS, DocumentStatus.COMPLETED, DocumentStatus.WAITING));
        Page<Document> byStatusInAndDeletedFalse = documentRepository.findByStatusInAndDeletedFalseOrderByCreatedAtDesc(statusList, pageable);
        return new ResPageable(
                byStatusInAndDeletedFalse.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                pageable.getPageNumber(),
                byStatusInAndDeletedFalse.getTotalPages(),
                byStatusInAndDeletedFalse.getTotalElements()
        );
    }

    @Override
    public ResPageable getAlllAcceptedApplication(User user, Pageable pageable) {
        List<DocumentStatus> statusList = new ArrayList<>(Arrays.asList(DocumentStatus.CREATED, DocumentStatus.INPROCESS, DocumentStatus.COMPLETED));
        Page<Document> byStatusInAndDeletedFalse = documentRepository.findByStatusInAndDeletedFalseAndCheckedBySectionOrderByCreatedAtDesc(statusList, user.getSection(), pageable);
        return new ResPageable(
                byStatusInAndDeletedFalse.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                pageable.getPageNumber(),
                byStatusInAndDeletedFalse.getTotalPages(),
                byStatusInAndDeletedFalse.getTotalElements()
        );
    }

    @Override
    public ResPageable getAllDocumentToSend(User user, Pageable pageable) {
        List<AnswerStatus> statusList = new ArrayList<>(Arrays.asList(AnswerStatus.CREATED, AnswerStatus.ACCEPTED));
        List<Answer> findAnswers = answerRepository.findByStatusInAndDeletedFalse(statusList);
        if (!findAnswers.isEmpty()) {
            Page<Document> byStatusInAndAnswerIsNotNullAndDeletedFalse = documentRepository.findByAnswerInAndDeletedFalseAndCheckedByOrderByCreatedAtDesc(findAnswers, pageable, user);
            return new ResPageable(
                    byStatusInAndAnswerIsNotNullAndDeletedFalse.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                    pageable.getPageNumber(),
                    byStatusInAndAnswerIsNotNullAndDeletedFalse.getTotalPages(),
                    byStatusInAndAnswerIsNotNullAndDeletedFalse.getTotalElements()
            );
        }
        return new ResPageable(new ArrayList<>(), pageable.getPageNumber(), 0, 0);
    }

    @Override
    public List<CustomInfoCount> getCountByCheckedBy() {
        return documentRepository.getDocumentByCheckedBy();
    }

    @Override
    public ResPageable getAnswerFeedback(User user, Pageable pageable) {
        Page<Document> findDocuments = documentRepository.findByCheckedByAndStatusAndDeletedFalseOrderByCreatedAtDesc(user, DocumentStatus.COMPLETED, pageable);
        return new ResPageable(
                findDocuments.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                pageable.getPageNumber(),
                findDocuments.getTotalPages(),
                findDocuments.getTotalElements()
        );
    }

    @Override
    public ApiResponse changeSection(UUID documentId, Long sectionId) {
        try{
            Document document = documentRepository.findById(documentId).orElseThrow(() -> new IllegalStateException("Document not found for set section"));
            document.setSection(sectionRepository.findById(sectionId).orElseThrow(()->new IllegalStateException("Section not found for set section")));
            document.setStatus(DocumentStatus.FORWARD_TO_MODERATOR);
            documentRepository.save(document);
            return new ApiResponse("Success!!",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error!!!",false);
        }

    }

    public ResPageable getAllApplicationListenerIsNull(int page, int size,User user,String sts) {
        Section section = sectionRepository.findById(user.getSection().getId()).orElseThrow(() -> new IllegalStateException("Section not found!!!"));

        Pageable pageable = CommonUtils.getPageable(page, size);
        Page<Document> findDocuments =null;
        if (sts.equals(UserStatus.SUPER_MODERATOR.name())){
            findDocuments = documentRepository.findByStatusAndAnswerIsNullAndDeletedFalseOrderByCreatedAtDesc(DocumentStatus.FORWARD_TO_SUPER_MODERATOR, pageable);
        }else {
            findDocuments=documentRepository.findByStatusAndSectionAndAnswerIsNullOrderByCreatedAtDesc(DocumentStatus.FORWARD_TO_MODERATOR,user.getSection(),pageable);
        }
        return new ResPageable(
                findDocuments.getContent().stream().map(DocumentResponse::fromEntity).collect(Collectors.toList()),
                page,
                findDocuments.getTotalPages(),
                findDocuments.getTotalElements()
        );
    }
}
