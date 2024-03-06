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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_usuario",unique = true)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_examen",unique = true)
    private Examen examen;
}
