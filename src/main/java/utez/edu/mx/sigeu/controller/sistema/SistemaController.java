package utez.edu.mx.sigeu.controller.sistema;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.sistema.dto.ColoresDto;
import utez.edu.mx.sigeu.service.sistema.SistemaService;

@Controller
@RequestMapping("/api/sistema")
@CrossOrigin(origins = {"*"})
public class SistemaController {
    private final SistemaService service;

    public SistemaController(SistemaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> saveColors(@RequestBody ColoresDto coloresDto) {
        return service.save(coloresDto.toEntity());
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> updateColors(@RequestBody ColoresDto coloresDto){
        return service.update(coloresDto.toEntity());
    }



}
