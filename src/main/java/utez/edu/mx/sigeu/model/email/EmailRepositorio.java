package utez.edu.mx.sigeu.model.email;

import org.springframework.stereotype.Repository;
import utez.edu.mx.sigeu.controller.email.dto.EmailDto;

@Repository
public interface EmailRepositorio {
    public void sendEmail(EmailDto emailDto);
}
