package utez.edu.mx.sigeu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.person.PersonRepository;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.role.RoleRepository;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.model.usuario.UsuarioRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final UsuarioRepository userRepository;
    private final PasswordEncoder encoder;


    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
        Role adminRole = getOrSaveRole(new Role("ADMIN_ROLE"));
        getOrSaveRole(new Role("DOCENTE_ROLE"));
        getOrSaveRole(new Role("ESTUDIANTE_ROLE"));
        Person person = getOrSavePerson(
                new Person("Agles","Avelar","Ocampo","20223tn005","AEOA")
        );
        Usuario user = getOrSaveUser(
                new Usuario("Agles",encoder.encode("agles"),true,person)
        );
        saveUserRoles(user.getId(), adminRole.getId());
    }
    @Transactional
    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        return foundRole.orElseGet(() -> {
            roleRepository.saveAndFlush(role);
            return roleRepository.findByName(role.getName()).orElse(null);
        });
    }

    @Transactional
    public Person getOrSavePerson(Person person) {
        Optional<Person> foundPerson = personRepository.findByCurp(person.getCurp());
        return foundPerson.orElseGet(() -> personRepository.saveAndFlush(person));
    }
    @Transactional
    public Usuario getOrSaveUser(Usuario user) {
        Optional<Usuario> foundUser = userRepository.findByEmail(user.getEmail());
        return foundUser.orElseGet(() -> {
            Role userRole = user.getRole();
            if (userRole != null) {
                if (userRole.getId() == null) {
                    userRole = getOrSaveRole(userRole);
                }
            }
            Person userPerson = user.getPerson();
            if (userPerson != null) {
                if (userPerson.getId() == null) {
                    userPerson = getOrSavePerson(userPerson);
                }
            }
            user.setRole(userRole);
            user.setPerson(userPerson);
            return userRepository.saveAndFlush(user);
        });
    }

    @Transactional
    public void saveUserRoles(Long userId, Long roleId) {
        Usuario usuario = userRepository.findById(userId).orElse(null);
        if (usuario != null) {
            Role newRole = roleRepository.findById(roleId).orElse(null);
            if (newRole != null) {
                usuario.setRole(newRole);
                userRepository.save(usuario); // Asegura que el Usuario esté en estado "managed"
            }
        }
    }




}
