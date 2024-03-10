package utez.edu.mx.sigeu.service.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.person.PersonRepository;
import utez.edu.mx.sigeu.model.role.RoleRepository;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.model.usuario.UsuarioRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonRepository personRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }


    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(
                usuarioRepository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(Usuario usuario){
        System.out.println(usuario.toString());
        Optional<Usuario> foundUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (foundUsuario.isPresent())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "EmailAlreadyExist"
            ),HttpStatus.BAD_REQUEST);

        usuario.setStatus(true);
        usuario.getPerson().setUsuario(usuario);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario = usuarioRepository.saveAndFlush(usuario);

         return new ResponseEntity<>(new ApiResponse(
                 usuario,
                 HttpStatus.OK
         ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Usuario> foundPerson = usuarioRepository.findById(id);
        if (foundPerson.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "IdNotFound"
            ),HttpStatus.BAD_REQUEST);
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                true,
                "UsuarioEliminado"
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Usuario> foundUser = usuarioRepository.findById(id);
        if (foundUser.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "UsuarioNotFound"
            ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                usuarioRepository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }


}
