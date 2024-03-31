package utez.edu.mx.sigeu.controller.clase;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.clase.dto.ClaseDto;
import utez.edu.mx.sigeu.service.clase.ClaseService;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/clase")
public class ClaseController {

    private final ClaseService service;

    public ClaseController(ClaseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody ClaseDto claseDto){
        return service.register(claseDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody ClaseDto claseDto){
        return service.update(claseDto.toEntity());
    }
}
