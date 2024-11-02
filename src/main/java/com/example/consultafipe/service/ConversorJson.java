package com.example.consultafipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorJson {
  private final ObjectMapper MAPPER = new ObjectMapper();

  public <T> T convertFromJson(String json, Class<T> classe) {
    try {
      return MAPPER.readValue(json, classe);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Erro ao converter Json para objeto Java. " + e);
    }
  }

  public <T> List<T> convertFromJsonArray(String jsonArray, Class<T> classe) {
    try {
      return MAPPER.readValue(jsonArray, MAPPER.getTypeFactory().constructCollectionType(List.class, classe));
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter um array Json para lista de objetos Java. " + e);
    }

  }
}
