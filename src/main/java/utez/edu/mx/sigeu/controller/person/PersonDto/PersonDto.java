package utez.edu.mx.sigeu.controller.person.PersonDto;

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
public class PersonDto {
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private String matricula;
    private String curp;

    // Datos del usuario
    private String userEmail;
    private String userPassword;
    private boolean userStatus;
    private Long roleId; // ID del rol

    public Person toEntity(){
        Person person = new Person(id, name, lastname, surname, matricula, curp);

        // Crear el usuario si se proporcionan datos
        if (userEmail != null && userPassword != null && roleId != null) {
            Usuario usuario = new Usuario();
            usuario.setEmail(userEmail);
            usuario.setPassword(userPassword);
            usuario.setStatus(userStatus);
            // Asignar el rol
            Role role = new Role();
            role.setId(roleId);
            usuario.setRole(role);
            // Asignar el usuario a la persona
            person.setUsuario(usuario);
        }

        return person;
    }
}
