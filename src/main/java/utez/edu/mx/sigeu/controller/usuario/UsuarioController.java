package utez.edu.mx.sigeu.controller.usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.usuario.usuarioDto.UsuarioDto;
import utez.edu.mx.sigeu.service.usuario.UsuarioService;

@Controller
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"*"})
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return usuarioService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UsuarioDto usuarioDto){
        return usuarioService.save(usuarioDto.toEntity());
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return usuarioService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }
}
