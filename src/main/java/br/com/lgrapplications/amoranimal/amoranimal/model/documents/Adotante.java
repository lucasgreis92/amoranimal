package br.com.lgrapplications.amoranimal.amoranimal.model.documents;

import br.com.lgrapplications.amoranimal.amoranimal.model.Devolucao;
import br.com.lgrapplications.amoranimal.amoranimal.model.Ocorrencia;
import br.com.lgrapplications.amoranimal.amoranimal.model.Visita;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Adotante {

    @Id
    private String id;

    private String nome;

    private String cpf;

    private String rg;

    private String cep;

    private String logradouro;

    private Long numero;

    private String bairro;

    private String cidade;

    private String tel1;

    private String tel2;

    private String sexo;

    private String nomeAnimal;

    private String tpAnimal;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dtUltAcomp;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dtAdocao;

    private String urlImagem = "img/no-image.png";

    private List<Visita> visitas = new ArrayList<>();

    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    private List<Devolucao> devolucoes = new ArrayList<>();

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getTpAnimal() {
        return tpAnimal;
    }

    public void setTpAnimal(String tpAnimal) {
        this.tpAnimal = tpAnimal;
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

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
