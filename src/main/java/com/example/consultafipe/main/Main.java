package com.example.consultafipe.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.consultafipe.modelos.Dados;
import com.example.consultafipe.modelos.Modelo;
import com.example.consultafipe.modelos.Modelos;
import com.example.consultafipe.modelos.Montadora;
import com.example.consultafipe.modelos.Veiculo;
import com.example.consultafipe.service.ConsultaAPI;
import com.example.consultafipe.service.ConversorJson;

public class Main {

    Scanner scanner = new Scanner(System.in);
    ConsultaAPI consultaAPI = new ConsultaAPI();
    ConversorJson conversor = new ConversorJson();

    final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
public void exibeMenu(){
    System.out.println("""
        Selecione o tipo de veiculo que gostaria de consultar:
          1 - Carro
          2 - Moto
          3 - Caminhão""");
    int tipoDeVeiculo = scanner.nextInt();
    scanner.nextLine();

    String endereco = null;
    switch (tipoDeVeiculo) {
      case 1:
        endereco = URL_BASE + "carros/marcas/";
        break;
      case 2:
        endereco = URL_BASE + "motos/marcas/";
      case 3:
        endereco = URL_BASE + "caminhoes/marcas/";
    }

    String json = consultaAPI.consultaAPI(endereco);
    List<Montadora> montadoras = conversor.convertFromJsonArray(json, Montadora.class);
    montadoras.forEach(System.out::println);

    System.out.println("\nDigite o código da montadora do veiculo que esta procurando:");
    int codigoMontadora = scanner.nextInt();
    scanner.nextLine();

    endereco = endereco + codigoMontadora + "/modelos/";
    json = consultaAPI.consultaAPI(endereco);

    Modelo modelo = conversor.convertFromJson(json, Modelo.class);
    modelo.modelos().forEach(System.out::println);

    System.out.println("\nDigite um trecho do nome do modelo que deseja visualizar");
    String trechoModelo = scanner.nextLine();

    List<Modelos> modelosFiltrados = modelo.modelos().stream()
        .filter(m -> m.nome().toUpperCase().contains(trechoModelo.toUpperCase()))
        .collect(Collectors.toList());

    modelosFiltrados.forEach(System.out::println);

    System.out.println("\nDigite o código do modelo para buscar os valores do veículo");
    int codigoVeiculo = scanner.nextInt();
    scanner.nextLine();

    endereco = endereco + codigoVeiculo + "/anos/";
    json = consultaAPI.consultaAPI(endereco);

    List<Dados> anos = conversor.convertFromJsonArray(json, Dados.class);

    List<Veiculo> veiculos = new ArrayList<>();

    for (Dados dado : anos) {
      String enderecoPorAno = endereco + dado.codigo();
      json = consultaAPI.consultaAPI(enderecoPorAno);
      Veiculo veiculo = conversor.convertFromJson(json, Veiculo.class);
      veiculos.add(veiculo);
    }

    System.out.println("\nInformações de todos os anos encontrados para o veículo buscado:");
    veiculos.forEach(System.out::println);
    scanner.close();
  }
}
