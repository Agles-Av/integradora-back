package utez.edu.mx.sigeu.controller.usuario_examen;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.usuario_examen.dto.UsuarioExamenDto;
import utez.edu.mx.sigeu.service.usuario_examen.UsuarioExamenService;

@Controller
@RequestMapping("/api/usuarioexamen")
@CrossOrigin(origins = {"*"})
public class UsuarioExamenController {
    private final UsuarioExamenService service;

    public UsuarioExamenController(UsuarioExamenService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById (@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save (@Valid @RequestBody UsuarioExamenDto dto){
        return service.save(dto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById (@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/examen/{idUsuario}")
    public ResponseEntity<ApiResponse> findExamenById(@PathVariable Long idUsuario){
        return service.findAllByIdUser(idUsuario);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> update (@Valid @RequestBody UsuarioExamenDto dto){
        return service.update(dto.toEntity());
    }
}
