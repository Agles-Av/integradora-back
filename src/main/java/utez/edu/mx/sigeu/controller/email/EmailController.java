package utez.edu.mx.sigeu.controller.email;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.email.dto.EmailDto;
import utez.edu.mx.sigeu.model.email.EmailRepositorio;

@Controller
@RequestMapping("/api/email")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailController {
    private final EmailRepositorio emailRepositorio;

    public EmailController(EmailRepositorio emailRepositorio) {
        this.emailRepositorio = emailRepositorio;
    }

    @PostMapping("/send-email")
    private ResponseEntity<ApiResponse> sendEmail(@RequestBody EmailDto email) throws MessagingException {
        try{
            emailRepositorio.sendEmail(email);
            return new ResponseEntity<>(new ApiResponse(email, HttpStatus.OK), HttpStatus.OK);
        } catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,true, runtimeException.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
}
