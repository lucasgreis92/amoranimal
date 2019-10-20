package br.com.lgrapplications.amoranimal.amoranimal.service;


import br.com.lgrapplications.amoranimal.amoranimal.model.Mensagem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {

    public Mensagem findNewMensagem(List mensagens){
        if (mensagens == null){
            return null;
        }
        for (Object msgaux : mensagens){
            Mensagem msg = (Mensagem)msgaux;
            if (msg.isNova()){
                msg.setNova(false);
                return msg;
            }
        }
        return null;
    }
}
