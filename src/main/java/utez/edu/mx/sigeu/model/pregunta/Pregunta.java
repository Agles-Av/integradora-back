package utez.edu.mx.sigeu.model.pregunta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pregunta")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 256)
    private String name;
    @Column
    private boolean tipo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @JsonIgnore
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.PERSIST)
    private List<Respuesta> respuestas;

    @JsonIgnore
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.PERSIST)
    private List<RespuestaUsuario> respuestaUsuario;



}
