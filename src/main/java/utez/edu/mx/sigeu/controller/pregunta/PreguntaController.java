package utez.edu.mx.sigeu.controller.pregunta;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.pregunta.dto.PreguntaDto;
import utez.edu.mx.sigeu.service.pregunta.PreguntaService;

@Controller
@RequestMapping("/api/pregunta")
@CrossOrigin(origins = {"*"})
public class PreguntaController {
    private final PreguntaService service;

    public PreguntaController(PreguntaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody PreguntaDto dto){
        return service.register(dto.toEntity());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse> findById (@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteById (@PathVariable Long id){
        return service.deleteById(id);
    }

    @PutMapping("/")
    public  ResponseEntity<ApiResponse> update (@Valid @RequestBody PreguntaDto dto){
        return service.update(dto.toEntity());
    }
}
