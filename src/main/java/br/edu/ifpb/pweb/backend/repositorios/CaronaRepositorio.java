package br.edu.ifpb.pweb.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.pweb.backend.modelos.Carona;

public interface CaronaRepositorio extends JpaRepository<Carona, Long> {
    List<Carona> findByFinalizadaAndMotoristaEmail(Boolean finalizada, String motoristaEmail);
}
