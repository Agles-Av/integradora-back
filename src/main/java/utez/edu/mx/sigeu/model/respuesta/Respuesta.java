package utez.edu.mx.sigeu.model.respuesta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String nombre;
    @Column
    private boolean correcta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    @JsonIgnore
    @OneToOne(mappedBy = "respuesta", cascade = CascadeType.PERSIST)
    private RespuestaUsuario respuestaUsuario;
}
