package utez.edu.mx.sigeu.controller.respuesta;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.respuesta.dto.RespuestaDto;
import utez.edu.mx.sigeu.service.respuesta.RespuestaService;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/respuesta")
public class RespuestaController {
    private final RespuestaService service;

    public RespuestaController(RespuestaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save (@Valid @RequestBody RespuestaDto dto){
        return service.save(dto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
}
