package utez.edu.mx.sigeu.controller.email.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private String destinatario;
    private String asunto;
    private String profesor;
    private String clase;
    private String examen;
    private int calificacion;



}
