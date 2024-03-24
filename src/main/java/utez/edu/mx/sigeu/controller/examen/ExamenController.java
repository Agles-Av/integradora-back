package utez.edu.mx.sigeu.controller.examen;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.examen.dto.ExamenDto;
import utez.edu.mx.sigeu.service.examem.ExamenService;


@Controller
@RequestMapping("/api/examen")
@CrossOrigin(origins = {"*"})
public class ExamenController {
    private final ExamenService service;

    public ExamenController(ExamenService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody ExamenDto dto){
        return service.register(dto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return service.detele(id);
    }

    @PostMapping("/{code}")
    public ResponseEntity<ApiResponse> foundByCode (@PathVariable String code){
        return service.findByCode(code);
    }
}
