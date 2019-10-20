package br.com.lgrapplications.amoranimal.amoranimal.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Mensagem {

    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime data;

    private boolean nova = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isNova() {
        return nova;
    }

    public void setNova(boolean nova) {
        this.nova = nova;
    }
}
