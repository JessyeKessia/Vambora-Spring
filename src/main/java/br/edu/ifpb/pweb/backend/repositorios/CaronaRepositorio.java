package br.edu.ifpb.pweb.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.pweb.backend.modelos.Carona;
import java.util.Optional;

public interface CaronaRepositorio extends JpaRepository<Carona, Long> {
}
