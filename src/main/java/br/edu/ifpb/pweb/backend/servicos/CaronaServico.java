package br.edu.ifpb.pweb.backend.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb.backend.dtos.CaronaDTO;
import br.edu.ifpb.pweb.backend.modelos.Carona;
import br.edu.ifpb.pweb.backend.repositorios.CaronaRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CaronaServico {
    @Autowired
    private CaronaRepositorio caronaRepositorio;

    public Carona criarCarona(CaronaDTO carona) throws Exception {
        validarDados(carona);

        Carona novaCarona = Carona.builder()
            .motoristaNome(carona.motoristaNome())
            .motoristaEmail(carona.motoristaEmail())
            .dataDeSaida(carona.dataDeSaida())
            .enderecoDePartida(carona.enderecoDePartida())
            .enderecoDeChegada(carona.enderecoDeChegada())
            .valor(carona.valor())
            .vagas(carona.vagas())
            .observacoes(carona.observacoes())
            .finalizada(carona.finalizada())
            .build();
        
        return caronaRepositorio.save(novaCarona); 
    }

    public List<Carona> listarTodas(String motoristaEmail, Boolean finalizada) {
        return caronaRepositorio.findByFinalizadaAndMotoristaEmail(finalizada, motoristaEmail);
    }

    public Optional<Carona> pegarCaronaPorId(Long id) {
        return caronaRepositorio.findById(id);
    }

    public void removerCarona(Long id) throws Exception {
        Optional<Carona> carona = caronaRepositorio.findById(id);

        if (carona.isEmpty())
            throw new Exception("Carona não existe");

        caronaRepositorio.delete(carona.get());
    }

    public Carona atualizarCarona(Long id, CaronaDTO caronaDTO) throws Exception {
        Optional<Carona> carona = caronaRepositorio.findById(id);

        if (carona.isEmpty())
            throw new Exception("Carona não existe");
        
        Carona caronaAtualizada = carona.get();
        caronaAtualizada.setMotoristaNome(caronaDTO.motoristaNome());
        caronaAtualizada.setMotoristaEmail(caronaDTO.motoristaEmail());
        caronaAtualizada.setDataDeSaida(caronaDTO.dataDeSaida());
        caronaAtualizada.setEnderecoDePartida(caronaDTO.enderecoDePartida());
        caronaAtualizada.setEnderecoDeChegada(caronaDTO.enderecoDeChegada());
        caronaAtualizada.setValor(caronaDTO.valor());
        caronaAtualizada.setVagas(caronaDTO.vagas());
        caronaAtualizada.setObservacoes(caronaDTO.observacoes());
        caronaAtualizada.setFinalizada(caronaDTO.finalizada());

        return caronaRepositorio.save(caronaAtualizada);
    }

    private void validarDados(CaronaDTO carona) throws Exception {
        if (carona.motoristaNome().trim().isEmpty()) {
            throw new Exception("motoristaNome não foi informado");
        }

        if (carona.motoristaEmail().trim().isEmpty()) {
            throw new Exception("motoristaEmail não foi informado");
        }

        if (carona.dataDeSaida().isBefore(LocalDate.now())) {
            throw new Exception("dataDeSaida inválida");
        }

        if (carona.enderecoDePartida().trim().isEmpty()) {
            throw new Exception("enderecoDePartida não foi informado");
        }

        if (carona.enderecoDeChegada().trim().isEmpty()) {
            throw new Exception("enderecoDeChegada não foi informado");
        }

        if (carona.valor() < 0) {
            throw new Exception("valor inválido");
        }

        if (carona.vagas() < 0) {
            throw new Exception("vagas inválidas");
        }
    }
}
