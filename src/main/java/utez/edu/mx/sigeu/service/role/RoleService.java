package utez.edu.mx.sigeu.service.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.role.RoleRepository;

import java.sql.SQLException;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(
                roleRepository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

}
