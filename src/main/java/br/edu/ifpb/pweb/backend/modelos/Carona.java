package br.edu.ifpb.pweb.backend.modelos;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "caronas")
public class Carona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String motoristaNome;

    @Column(nullable = false)
    private String motoristaEmail;

    @Column(nullable = false)
    private LocalDate dataDeSaida;

    @Column(nullable = false)
    private String enderecoDePartida;

    @Column(nullable = false)
    private String enderecoDeChegada;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer vagas;

    @Column(nullable = true)
    private String observacoes;

    @Column(nullable = true)
    private Boolean finalizada;
}
