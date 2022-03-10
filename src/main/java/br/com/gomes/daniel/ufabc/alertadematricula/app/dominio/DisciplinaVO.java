package br.com.gomes.daniel.ufabc.alertadematricula.app.dominio;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Periodo;

public class DisciplinaVO {
    public int campus;
    public int vagas;
    public Object obrigatoriedades;
    public Object horarios;
    public String codigo;
    public int vagas_ingressantes;
    public int creditos;
    public Object recomendacoes;
    public String nome_campus;
    public String nome;
    public String id;
    public Object tpi;

    public Disciplina toDomain(){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(this.nome);
        disciplina.setPeriodo(nomeToPeriodo());
        disciplina.setVagasDisponibilizadas(this.vagas);
        disciplina.setVagasIngressantes(this.vagas_ingressantes);
        disciplina.setCreditos(this.creditos);
        disciplina.setCodigo(this.codigo);
        disciplina.setCampus(this.nome_campus);
        disciplina.setIdentificadorUFABC(this.id);
        return disciplina;
    }


    private Periodo nomeToPeriodo() {
        if (this.nome.contains("Noturno")){
            return Periodo.NOTURNO;
        }
        if (this.nome.contains("Matutino"))
            return Periodo.MATUTINO;

        if (this.nome.contains("Vespertino")){
            return Periodo.VESPERTINO;
        }
        return null;
    }

}
