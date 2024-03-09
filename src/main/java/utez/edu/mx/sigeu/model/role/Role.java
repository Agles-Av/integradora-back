package utez.edu.mx.sigeu.model.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "role")
public class Role {
    //datos de Role
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false, unique = true)
    private String name;


    //relaciones
    @OneToMany(mappedBy = "role",cascade = CascadeType.PERSIST)
    private List<Usuario> usuario;

    public Role(String name) {
        this.name = name;
    }
}
