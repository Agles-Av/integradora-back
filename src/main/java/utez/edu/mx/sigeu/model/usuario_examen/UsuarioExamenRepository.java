package utez.edu.mx.sigeu.model.usuario_examen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioExamenRepository extends JpaRepository<UsuarioExamen,Long> {
List<UsuarioExamen> findAllByUsuario_Id(Long id);
}
