package utez.edu.mx.sigeu.service.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import utez.edu.mx.sigeu.controller.email.dto.EmailDto;
import utez.edu.mx.sigeu.model.email.EmailRepositorio;

import java.util.Optional;

@Service
public class EmailService implements EmailRepositorio {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailDto emailDto) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailDto.getDestinatario());
            helper.setSubject(emailDto.getAsunto());

            // Procesar la plantilla Thymeleaf
            Context context = new Context();
            context.setVariable("Profesor", emailDto.getExamen().getExamen().getClase().getUsuario().getPerson().getName());
            context.setVariable("clase", emailDto.getExamen().getExamen().getClase().getName());
            context.setVariable("nombreExamen", emailDto.getExamen().getExamen().getTitle());
            context.setVariable("calificacion", emailDto.getExamen().getCalificacion());
            String contenidoHtml = templateEngine.process("email", context);

            helper.setText(contenidoHtml, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
