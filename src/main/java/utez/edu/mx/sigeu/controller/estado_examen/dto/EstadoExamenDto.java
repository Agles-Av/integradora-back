package utez.edu.mx.sigeu.controller.estado_examen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;

@Getter
@Setter
@NoArgsConstructor
public class EstadoExamenDto {
    private Long id;
    private String name_state;

    public EstadoExamen toEntity(){
        return new EstadoExamen(id,name_state);
    }
}
