package com.example.consultafipe.modelos;

public record Montadora(Integer codigo, String nome) {
  @Override
  public String toString(){
    return String.format("Código: %3d - Montadora: %s", codigo, nome);
  }
}
