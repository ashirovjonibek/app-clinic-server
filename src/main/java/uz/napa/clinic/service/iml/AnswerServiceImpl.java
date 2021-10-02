package uz.napa.clinic.service.iml;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.napa.clinic.entity.Answer;
import uz.napa.clinic.entity.Application;
import uz.napa.clinic.entity.Document;
import uz.napa.clinic.entity.enums.AnswerStatus;
import uz.napa.clinic.entity.enums.DocumentStatus;
import uz.napa.clinic.exception.BadRequestException;
import uz.napa.clinic.payload.AnswerRequest;
import uz.napa.clinic.payload.AnswerResponse;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.repository.*;
import uz.napa.clinic.service.AnswerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    final
    ApplicationServiceImpl applicationService;
    final
    UserRepository userRepository;
    final
    AnswerRepository answerRepository;
    private final AttachmentRepository attachmentRepository;
    private final ApplicationRepository applicationRepository;
    private final DocumentRepository documentRepository;


    public AnswerServiceImpl(@Lazy ApplicationServiceImpl applicationService, UserRepository userRepository, AnswerRepository answerRepository, AttachmentRepository attachmentRepository, ApplicationRepository applicationRepository, DocumentRepository documentRepository) {
        this.applicationService = applicationService;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.attachmentRepository = attachmentRepository;
        this.applicationRepository = applicationRepository;
        this.documentRepository = documentRepository;
    }


    @Override
    public ApiResponse create(AnswerRequest request, UUID applicationId) {
        Answer savedAnswer = answerRepository.save(fillAnswer(new Answer(), request));
        Optional<Application> currentApplication = applicationRepository.findById(applicationId);
        if (currentApplication.isPresent()) {
            Document findDocument = documentRepository.findByApplicationAndDeletedFalseAndStatus(currentApplication.get(), DocumentStatus.INPROCESS);
            if (findDocument != null) {
                findDocument.setAnswer(savedAnswer);
                documentRepository.save(findDocument);
                return new ApiResponse("Answer created!", true);
            } else {
                throw new BadRequestException("Document not found With Application ID: " + applicationId);
            }

        } else {
            throw new BadRequestException("Application not found with ID :" + applicationId);
        }
    }

    @Override
    public ApiResponse update(AnswerRequest request, UUID id) {
        Optional<Answer> findAnswer = answerRepository.findById(id);
        if (findAnswer.isPresent()) {
            answerRepository.save(fillAnswer(findAnswer.get(), request));
            return new ApiResponse("Successfully updated", true);
        } else {
            throw new BadRequestException("Answer not found By ID: " + id);
        }
    }

    @Override
    public ApiResponse updateWithDocument(AnswerRequest request, UUID documentId) {
        Optional<Document> byId = documentRepository.findById(documentId);
        if (byId.isPresent()) {
            Document document = byId.get();
            Optional<Answer> findAnswer = answerRepository.findById(document.getAnswer().getId());
            if (findAnswer.isPresent()) {
                Answer answer = fillAnswer(findAnswer.get(), request);
                answer.setStatus(AnswerStatus.CREATED);
                answerRepository.save(answer);
                document.setStatus(DocumentStatus.INPROCESS);
                documentRepository.save(document);
                return new ApiResponse("Successfully updated", true);
            } else {
                throw new BadRequestException("Answer not found With Document ID: " + documentId);
            }
        } else {
            throw new BadRequestException("Document not found By ID: " + documentId);
        }

    }

    @Override
    public AnswerResponse getOne(UUID id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return AnswerResponse.fromEntity(answer.get());
        } else {
            throw new BadRequestException("Answer not found with ID: " + id);
        }
    }

    @Override
    public ApiResponse delete(UUID id) {
        Optional<Answer> findAnswer = answerRepository.findById(id);
        if (findAnswer.isPresent()) {
            Answer answer = findAnswer.get();
            answer.setDeleted(true);
            answerRepository.save(answer);
            return new ApiResponse("Answer deleted !", true);
        } else {
            throw new BadRequestException("Answer not found with ID: " + id);
        }
    }

    @Override
    public List<AnswerResponse> list(UUID id) {
        List<Answer> allByUserid = answerRepository.findByCreatedBy(id);
        return allByUserid.stream().map(this::getAnswer).collect(Collectors.toList());
    }

    @Override
    public ApiResponse sendBoss(UUID id) {
        Optional<Answer> findAnswer = answerRepository.findById(id);
        if (findAnswer.isPresent()) {
            Optional<Document> findDocument = documentRepository.findByAnswer(findAnswer.get());
            if (findDocument.isPresent()) {
                Document document = findDocument.get();
                document.setStatus(DocumentStatus.WAITING);
                Answer answer = findAnswer.get();
                answer.setStatus(AnswerStatus.WAITING);
                documentRepository.save(document);
                answerRepository.save(answer);
                return new ApiResponse("Answer sended to Boss", true);
            } else {
                throw new BadRequestException("Document not found by Answer ID: " + id);
            }
        } else {
            throw new BadRequestException("Answer not found with ID: " + id);
        }
    }

    @Override
    public ApiResponse sendApplicant(UUID id) {
        Optional<Answer> findAnswer = answerRepository.findById(id);
        if (findAnswer.isPresent()) {
            Optional<Document> byAnswer = documentRepository.findByAnswer(findAnswer.get());
            if (byAnswer.isPresent()) {
                Answer answer = findAnswer.get();
                answer.setStatus(AnswerStatus.COMPLETED);
                answerRepository.save(answer);
                return new ApiResponse("Answer sended to Applicant", true);
            } else {
                throw new BadRequestException("Document not found by Answer ID: " + id);
            }

        } else {
            throw new BadRequestException("Answer not found with ID: " + id);
        }
    }

    public List<Answer> getByStatus(AnswerStatus status) {
        return answerRepository.findByStatusAndDeletedFalse(status);
    }

    public AnswerResponse getAnswer(Answer answer) {
        return AnswerResponse.fromEntity(answer);
    }

    private Answer fillAnswer(Answer answer, AnswerRequest request) {
        if (answer.getId() != null) {
            answer.setStatus(request.getStatus());
        } else {
            answer.setStatus(AnswerStatus.CREATED);
        }
        answer.setDescription(request.getDescription());
        if (!request.getAttachmentId().isEmpty()) {
            answer.setAttachments(attachmentRepository.findAllById(request.getAttachmentId()));
        }
        if (request.getDeniedMessage() != null) {
            answer.setDeniedMessage(request.getDeniedMessage());
        }
        if (request.getComment() != null) {
            answer.setComment(request.getComment());
        }
        if (answer.isLiked() || !answer.isLiked()) {
            answer.setLiked(request.isLiked());
        }
        return answer;
    }
}
