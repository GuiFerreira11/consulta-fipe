package com.example.consultafipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {

  public String consultaAPI(String endereco) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
    HttpResponse<String> response = null;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Erro na consulta a API. " + e);
    }
    return response.body();
  }
}
