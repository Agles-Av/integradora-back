package utez.edu.mx.sigeu.controller.usuario.usuarioDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.usuario.Usuario;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String email;
    private String password;
    private Boolean  status;
    private Role role;
    private Person person;

    public Usuario toEntity(){
        if (role == null || person == null)
            return new Usuario(id,email,password,status);
        return new Usuario(id,email,password,status,role,person);

    }

}
