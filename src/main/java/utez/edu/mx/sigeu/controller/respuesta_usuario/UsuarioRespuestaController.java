package utez.edu.mx.sigeu.controller.respuesta_usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.respuesta_usuario.dto.RespuestaUsuarioDto;
import utez.edu.mx.sigeu.service.respuesta_usuario.RespuestaUsuarioService;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/usuariorespuesta")
public class UsuarioRespuestaController {
    private final RespuestaUsuarioService service;

    public UsuarioRespuestaController(RespuestaUsuarioService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save (@Valid @RequestBody RespuestaUsuarioDto dto){
        return service.save(dto.toEntity());
    }
}
