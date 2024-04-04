package utez.edu.mx.sigeu.controller.email.dto;

import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;

@Getter
@Setter
public class EmailDto {

    private String destinatario;
    private String asunto;
    private UsuarioExamen examen;


}
