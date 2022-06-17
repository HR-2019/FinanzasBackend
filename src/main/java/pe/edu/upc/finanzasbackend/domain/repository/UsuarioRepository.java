package pe.edu.upc.finanzasbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.finanzasbackend.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



}
