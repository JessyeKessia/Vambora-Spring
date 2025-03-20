package br.edu.ifpb.pweb.backend.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifpb.pweb.backend.dtos.CaronaDTO;
import br.edu.ifpb.pweb.backend.modelos.Carona;
import br.edu.ifpb.pweb.backend.servicos.CaronaServico;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/caronas")
@CrossOrigin
public class CaronaControlador {
    @Autowired
    private CaronaServico caronaServico;

    @GetMapping
    public ResponseEntity<List<Carona>> listarCaronas(
        @RequestParam(required = true) String motoristaEmail,
        @RequestParam(defaultValue = "false") boolean finalizada
    ) {
        return ResponseEntity.ok(caronaServico.listarTodas(motoristaEmail, finalizada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carona> pegarCarona(@PathVariable Long id) {
        Optional<Carona> carona = caronaServico.pegarCaronaPorId(id);

        if (carona.isPresent()) {
            return ResponseEntity.ok(carona.get());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Carona> criarCarona(@RequestBody CaronaDTO caronaDTO) {
        try {
            return ResponseEntity.status(201).body(caronaServico.criarCarona(caronaDTO));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carona> atualizarCarona(@PathVariable Long id, @RequestBody CaronaDTO caronaDTO) {
        try {
            return ResponseEntity.ok(caronaServico.atualizarCarona(id, caronaDTO));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carona> deletarCarona(@PathVariable Long id) {
        try {
            caronaServico.removerCarona(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
