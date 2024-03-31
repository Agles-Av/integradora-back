package utez.edu.mx.sigeu.controller.estado_examen;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.estado_examen.dto.EstadoExamenDto;
import utez.edu.mx.sigeu.service.estado_exame.EdExamenService;

@Controller
@RequestMapping("/api/estadoE")
@CrossOrigin(origins = {"*"})
public class EstadoExamenController {

    private final EdExamenService service;

    public EstadoExamenController(EdExamenService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody EstadoExamenDto dto){
        return service.registe(dto.toEntity());
    }

    @PutMapping("/changeStatus/{idExamen}/{id}")
    public ResponseEntity<ApiResponse> changeState(@PathVariable Long idExamen, @PathVariable Long id){
        return service.changeState(idExamen, id);
    }
}
