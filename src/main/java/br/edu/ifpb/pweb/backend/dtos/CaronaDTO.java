package br.edu.ifpb.pweb.backend.dtos;

import java.time.LocalDate;

public record CaronaDTO(
        String motoristaNome,
        String motoristaEmail,
        LocalDate dataDeSaida,
        String enderecoDePartida,
        String enderecoDeChegada,
        Double valor,
        Integer vagas,
        String observacoes,
        Boolean finalizada
) {
}
