package br.edu.ifpb.pweb.backend.dtos;

import java.time.LocalDate;

public record CaronaDTO(
        String motoristaId,
        LocalDate dataDeSaida,
        String enderecoDePartida,
        String enderecoDeChegada,
        Double valor,
        Integer vagas,
        String observacoes
) {
}
