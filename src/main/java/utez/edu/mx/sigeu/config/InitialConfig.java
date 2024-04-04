package utez.edu.mx.sigeu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamenRepository;
import utez.edu.mx.sigeu.model.logo.Logo;
import utez.edu.mx.sigeu.model.logo.LogoORepository;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.person.PersonRepository;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.role.RoleRepository;
import utez.edu.mx.sigeu.model.sistema.Sistema;
import utez.edu.mx.sigeu.model.sistema.SistemaRepository;
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
    private final EstadoExamenRepository   estadoExamenRepository;
    private final SistemaRepository sistemaRepository;
    private final LogoORepository logoRepository;


    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
        Sistema sistema = saveSistema(new Sistema("#119DA4","#0C7489","#13505B"));
        Logo logo = saveLogo(new Logo("data:image/jpeg;base64,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"));

        EstadoExamen estadoExamen = saveEstadoExamen(new EstadoExamen("Listo"));
        EstadoExamen estadoExamen1 = saveEstadoExamen(new EstadoExamen("Pendiente"));
        EstadoExamen estadoExamen2 = saveEstadoExamen(new EstadoExamen("No publicado"));
        EstadoExamen estadoExamen3 = saveEstadoExamen(new EstadoExamen("Activo"));

        Role adminRole = getOrSaveRole(new Role("ADMIN_ROLE"));
        getOrSaveRole(new Role("DOCENTE_ROLE"));
        getOrSaveRole(new Role("ESTUDIANTE_ROLE"));
        Person person = getOrSavePerson(
                new Person("Agles","Avelar","Ocampo","20223tn005","AEOA")
        );
        Usuario user = getOrSaveUser(
                new Usuario("Agles",encoder.encode("agles"),true, person)
        );
        saveUserRoles(user.getId(), adminRole.getId());

        saveEstadoExamen(estadoExamen);
        saveEstadoExamen(estadoExamen1);
        saveEstadoExamen(estadoExamen2);
        saveEstadoExamen(estadoExamen3);

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
                userRepository.save(usuario);
            }
        }
    }

    @Transactional
    public EstadoExamen saveEstadoExamen(EstadoExamen estadoExamen){
        Optional<EstadoExamen> foundEstadoExamen = estadoExamenRepository.findByName(estadoExamen.getName());
        return foundEstadoExamen.orElseGet(() -> estadoExamenRepository.saveAndFlush(estadoExamen));
    }

   @Transactional
    public Sistema saveSistema(Sistema sistema){
        Optional<Sistema> foundSistema = sistemaRepository.findByColor1(sistema.getColor1());
        return foundSistema.orElseGet(() -> sistemaRepository.saveAndFlush(sistema));
    }

    @Transactional
    public Logo saveLogo(Logo logo){
        Optional<Logo>  foundLogo = logoRepository.findByLogo(logo.getLogo());
        return foundLogo.orElseGet(() -> logoRepository.saveAndFlush(logo));
    }

}
