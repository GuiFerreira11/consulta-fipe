package com.example.consultafipe.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Modelo") String modelo,
    @JsonAlias("AnoModelo") int ano,
    @JsonAlias("Combustivel") String combustivel,
    @JsonAlias("Valor") String valor) {
  @Override
  public String toString() {
    return String.format("Modelo: %s - Ano: %d - Combustível: %s - Valor: %s", modelo, ano, combustivel, valor);
  }
}
