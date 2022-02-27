package br.com.gomes.daniel.ufabc.alertadematricula.app.domain;

import lombok.Data;

import java.util.List;
@Data
public class Horario {
    private List<String> horas;
    private String periodicidade;
    private int semana;
}
