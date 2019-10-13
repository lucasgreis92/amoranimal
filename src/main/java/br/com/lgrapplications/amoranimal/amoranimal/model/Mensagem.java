package br.com.lgrapplications.amoranimal.amoranimal.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Mensagem {

    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime data;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
