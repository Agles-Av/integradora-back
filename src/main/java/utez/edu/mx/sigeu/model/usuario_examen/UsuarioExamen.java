package utez.edu.mx.sigeu.model.usuario_examen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario_examen")
public class UsuarioExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean respondido;
    @Column(nullable = false)
    @Min(value = 0, message = "La calificación no puede ser menor que 0")
    @Max(value = 10, message = "La calificación no puede ser mayor que 10")
    private int calificacion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime endAt;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    public UsuarioExamen(Long id, boolean respondido, int calificacion,Usuario usuario, Examen examen,LocalDateTime endAt) {
        this.id = id;
        this.respondido = respondido;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.examen = examen;
        this.endAt = endAt;
    }

    public UsuarioExamen(boolean respondido, int calificacion, LocalDateTime endAt, Usuario usuario, Examen examen) {
        this.respondido = respondido;
        this.calificacion = calificacion;
        this.endAt = endAt;
        this.usuario = usuario;
        this.examen = examen;
    }
}
