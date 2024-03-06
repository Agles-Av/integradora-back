package utez.edu.mx.sigeu.model.estado_examen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "estado_examen")
public class EstadoExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40,nullable = false)
    private String name_state;

    @JsonIgnore
    @OneToOne(mappedBy = "examen",cascade = CascadeType.PERSIST)
    private Examen examen;
}
