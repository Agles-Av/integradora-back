package utez.edu.mx.sigeu.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne
    @JoinColumn(name = "id_role")
    @JsonIgnoreProperties(value = {"usuario"})
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"usuario"})
    private List<Clase> clase;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"usuario"})
    private UsuarioExamen usuarioExamen;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"usuario"})
    private List<RespuestaUsuario> respuestaUsuarios;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_person")
    private Person person;

    //constructores

    public Usuario(Long id, String email, String password,boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Usuario(Long id, String email, String password, boolean status,Role role, Person person) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.person = person;
    }

    public Usuario(String email, String password,boolean status, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
        this.status = status;
    }

    public Usuario(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
