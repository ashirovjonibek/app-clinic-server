package uz.napa.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.clinic.entity.enums.UserStatus;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.payload.MessageHelper;
import uz.napa.clinic.security.CurrentUser;
import uz.napa.clinic.security.CustomUserDetails;
import uz.napa.clinic.service.MessageCenterService;

import java.util.UUID;

@RestController
@RequestMapping("/api/message")
public class MessageCenterController {
    @Autowired
    MessageCenterService messageCenterService;

    @GetMapping
    public HttpEntity<?> getAll(@CurrentUser CustomUserDetails userDetails,
                                @RequestParam(defaultValue ="") UUID toId){
        if (userDetails.getUser()!=null){
            return ResponseEntity.ok(messageCenterService.userMessages(userDetails.getUser().getId(),toId));
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authorize");
        }
    }

    @PostMapping
    public HttpEntity<?> saveMessage(@CurrentUser CustomUserDetails userDetails, @RequestBody MessageHelper messageHelper){
        if (userDetails.getUser()!=null){
            if (messageHelper.getFromId()==null)messageHelper.setFromId(userDetails.getUser().getId());
            else messageHelper.setMessage(messageHelper.getMessage());
            return ResponseEntity.ok(messageCenterService.sendOrEditMessage(messageHelper));
        }else {
            return ResponseEntity.ok(new ApiResponse("User not authorize!!!",false));
        }
    }

    @PutMapping
    public HttpEntity<?> edit(@CurrentUser CustomUserDetails userDetails, @RequestBody MessageHelper messageHelper){
        if (userDetails.getUser()!=null){
            if (messageHelper.getFromId()==null)messageHelper.setFromId(userDetails.getUser().getId());
            else messageHelper.setMessage(messageHelper.getMessage());
            return ResponseEntity.ok(messageCenterService.sendOrEditMessage(messageHelper));
        }else {
            return ResponseEntity.ok(new ApiResponse("User not authorize!!!",false));
        }
    }

    @DeleteMapping
    public HttpEntity<?> delete(@CurrentUser CustomUserDetails userDetails, @RequestParam UUID messageId){
        if (userDetails.getUser()!=null){
            return ResponseEntity.ok(messageCenterService.deleteMessage(userDetails.getUser().getId(),messageId));
        }else {
            return ResponseEntity.ok(new ApiResponse("User not authorize!!!",false));
        }
    }

    @PostMapping("/generate-chat")
    public HttpEntity<?> generateChat(@CurrentUser CustomUserDetails userDetails,@RequestParam UUID toId){
        if (!userDetails.getUser().getStatus().equals(UserStatus.APPLICANT)){
            return ResponseEntity.ok(messageCenterService.generateChat(userDetails.getUser(),toId));
        }else {
            return ResponseEntity.ok("This user can not generate chat!!!");
        }
    }
}
