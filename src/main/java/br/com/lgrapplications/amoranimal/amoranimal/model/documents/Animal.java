package br.com.lgrapplications.amoranimal.amoranimal.model.documents;

import br.com.lgrapplications.amoranimal.amoranimal.model.Devolucao;
import br.com.lgrapplications.amoranimal.amoranimal.model.Ocorrencia;
import br.com.lgrapplications.amoranimal.amoranimal.model.Visita;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Animal {

    private String id;

    private String nome;

    private Double idade;

    private String sexo;

    private String tpAnimal;

    private boolean vacinaV8;

    private boolean vacinaRaiva;

    private boolean vermifugado;

    private boolean castrado;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dtUltAcomp;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dtAdocao;

    private List<Visita> visitas = new ArrayList<>();

    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    private List<Devolucao> devolucoes = new ArrayList<>();

    private String nomeAdotante;

    private String urlImagem = "img/no-image.png";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isVacinaV8() {
        return vacinaV8;
    }

    public void setVacinaV8(boolean vacinaV8) {
        this.vacinaV8 = vacinaV8;
    }

    public boolean isVacinaRaiva() {
        return vacinaRaiva;
    }

    public void setVacinaRaiva(boolean vacinaRaiva) {
        this.vacinaRaiva = vacinaRaiva;
    }

    public boolean isVermifugado() {
        return vermifugado;
    }

    public void setVermifugado(boolean vermifugado) {
        this.vermifugado = vermifugado;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public LocalDateTime getDtUltAcomp() {
        return dtUltAcomp;
    }

    public void setDtUltAcomp(LocalDateTime dtUltAcomp) {
        this.dtUltAcomp = dtUltAcomp;
    }

    public LocalDateTime getDtAdocao() {
        return dtAdocao;
    }

    public void setDtAdocao(LocalDateTime dtAdocao) {
        this.dtAdocao = dtAdocao;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public List<Devolucao> getDevolucoes() {
        return devolucoes;
    }

    public void setDevolucoes(List<Devolucao> devolucoes) {
        this.devolucoes = devolucoes;
    }

    public String getTpAnimal() {
        return tpAnimal;
    }

    public void setTpAnimal(String tpAnimal) {
        this.tpAnimal = tpAnimal;
    }

    public String getNomeAdotante() {
        return nomeAdotante;
    }

    public void setNomeAdotante(String nomeAdotante) {
        this.nomeAdotante = nomeAdotante;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
