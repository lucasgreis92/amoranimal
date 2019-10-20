package br.com.lgrapplications.amoranimal.amoranimal.model.documents;

import br.com.lgrapplications.amoranimal.amoranimal.model.Devolucao;
import br.com.lgrapplications.amoranimal.amoranimal.model.Ocorrencia;
import br.com.lgrapplications.amoranimal.amoranimal.model.Visita;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Adocao {

    @Id
    private String id;

    private Animal animal;

    private Adotante adotante;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dtAdocao = LocalDateTime.now();

    private LocalDateTime dtUltAcomp;

    private boolean ieAtivo = true;

    private List<Visita> visitas = new ArrayList<>();

    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    private List<Devolucao> devolucoes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
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

    public boolean isIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(boolean ieAtivo) {
        this.ieAtivo = ieAtivo;
    }

    public LocalDateTime getDtUltAcomp() {
        return dtUltAcomp;
    }

    public void setDtUltAcomp(LocalDateTime dtUltAcomp) {
        this.dtUltAcomp = dtUltAcomp;
    }
}
