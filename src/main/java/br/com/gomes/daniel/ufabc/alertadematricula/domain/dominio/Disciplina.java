package br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio;

import java.io.Serializable;

public class Disciplina implements Serializable {

    public String identificadorUFABC;
    private String nome;
    private Periodo periodo;
    private int vagasDisponibilizadas;
    private int vagasIngressantes;
    private int vagasDisponiveis;
    private int creditos;
    private String codigo;
    private String campus;

    public String getIdentificadorUFABC() {
        return identificadorUFABC;
    }

    public void setIdentificadorUFABC(String identificadorUFABC) {
        this.identificadorUFABC = identificadorUFABC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public int getVagasDisponibilizadas() {
        return vagasDisponibilizadas;
    }

    public void setVagasDisponibilizadas(int vagasDisponibilizadas) {
        this.vagasDisponibilizadas = vagasDisponibilizadas;
    }

    public int getVagasIngressantes() {
        return vagasIngressantes;
    }

    public void setVagasIngressantes(int vagasIngressantes) {
        this.vagasIngressantes = vagasIngressantes;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    @Override
    public String toString() {
        return "identificadorUFABC:" + identificadorUFABC + " nome:" + nome + " " + vagasDisponiveis + "/" + vagasDisponibilizadas;

    }
}
