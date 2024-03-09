package utez.edu.mx.sigeu.service.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.role.RoleRepository;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.model.usuario.UsuarioRepository;

import java.sql.SQLException;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;


    public RoleService(RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(
                roleRepository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    /*@Transactional(rollbackFor = {SQLException.class})
    public Usuario saveUserRole(Long userId, Long roleId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario != null) {
            utez.edu.mx.sigeu.model.role.Role newRole = roleRepository.findById(roleId).orElse(null);
            if (newRole != null) {
                usuario.setRole(newRole);
                usuarioRepository.save(usuario);
            }
        }
    }*/


}
