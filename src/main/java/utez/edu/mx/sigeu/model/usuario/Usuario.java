package utez.edu.mx.sigeu.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.clase.Clase;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    //Datos de usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20,nullable = false,unique = true)
    private String email;
    @Column(length = 256,nullable = false)
    private String password;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status; //no usar

    //Relaciones
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_role", unique = true)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Clase> clase;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private UsuarioExamen usuarioExamen;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.PERSIST)
    private List<RespuestaUsuario> respuestaUsuarios;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_person",unique = true)
    private Person person;

    //constructores

    public Usuario(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Usuario(Long id, String email, String password, Role role, Person person) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public Usuario(String email, String password, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
    }

    public Usuario(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
