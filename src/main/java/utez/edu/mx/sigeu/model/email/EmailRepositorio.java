package utez.edu.mx.sigeu.model.email;

import utez.edu.mx.sigeu.controller.email.dto.EmailDto;

public interface EmailRepositorio {
    public void sendEmail(EmailDto emailDto);
}
