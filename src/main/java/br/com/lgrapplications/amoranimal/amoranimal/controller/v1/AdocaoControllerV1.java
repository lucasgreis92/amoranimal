package br.com.lgrapplications.amoranimal.amoranimal.controller.v1;

import br.com.lgrapplications.amoranimal.amoranimal.base.DefaultController;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Adocao;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rs/v1/adocoes")
public class AdocaoControllerV1 extends DefaultController<Adocao> {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public AdocaoRepository getRepository() {
        return adocaoRepository;
    }
}
