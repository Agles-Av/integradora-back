package utez.edu.mx.sigeu.controller.usuario_examen.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;

@NoArgsConstructor
@Setter
@Getter
public class UsuarioExamenDto {
    private Long id;
    private boolean respondido;
    private int calificacion;
    private Usuario usuario;
    private Examen examen;

    public UsuarioExamen toEntity(){
        return new UsuarioExamen(id,respondido,calificacion,usuario,examen);
    }


}
