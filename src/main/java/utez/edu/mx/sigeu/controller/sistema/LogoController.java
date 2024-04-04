package utez.edu.mx.sigeu.controller.sistema;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.sistema.dto.LogoDto;
import utez.edu.mx.sigeu.service.sistema.LogoService;

@Controller
@RequestMapping("/api/logo")
@CrossOrigin(origins = {"*"})
public class LogoController {
    private final LogoService service;

    public LogoController(LogoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(LogoDto dto){
        return service.save(dto.toEntity());
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> update(@RequestBody LogoDto dto){
        return service.update(dto.toEntity());
    }


}
