package com.example.consultafipe.modelos;

public record Modelos(Integer codigo, String nome) {
  @Override
  public String toString(){
    return String.format("Código: %5d - Modelo: %s", codigo, nome);
  }
}
