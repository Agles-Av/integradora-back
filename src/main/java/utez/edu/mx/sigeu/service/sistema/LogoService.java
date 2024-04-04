package utez.edu.mx.sigeu.service.sistema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.logo.Logo;
import utez.edu.mx.sigeu.model.logo.LogoORepository;

import java.sql.SQLException;
import java.util.Optional;

@Transactional
@Service
public class LogoService {
    private final LogoORepository logoORepository;

    public LogoService(LogoORepository logoORepository) {
        this.logoORepository = logoORepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(
                logoORepository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save (Logo logoO){
        return new ResponseEntity<>(new ApiResponse(
                logoORepository.save(logoO),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Logo logoO){
        Optional<Logo> foundId = logoORepository.findById(logoO.getId());
        if (foundId.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "RecordNotFound"
            ),HttpStatus.BAD_REQUEST);
        foundId.get().setLogo(logoO.getLogo());
        return new ResponseEntity<>(new ApiResponse(
                logoORepository.save(logoO),
                HttpStatus.OK
        ),HttpStatus.OK);
    }


}
