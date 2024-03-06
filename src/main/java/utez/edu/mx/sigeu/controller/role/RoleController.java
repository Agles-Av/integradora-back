package utez.edu.mx.sigeu.controller.role;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.service.role.RoleService;

@Controller
@RequestMapping("/api/role")
@CrossOrigin(origins = {"*"})
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
       return roleService.getAll();
    }
}
