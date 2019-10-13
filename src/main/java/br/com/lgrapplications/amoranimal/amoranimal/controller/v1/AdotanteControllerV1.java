package br.com.lgrapplications.amoranimal.amoranimal.controller.v1;

import br.com.lgrapplications.amoranimal.amoranimal.base.DefaultController;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Adotante;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AdotanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rs/v1/adotantes")
public class AdotanteControllerV1 extends DefaultController<Adotante> {

    @Autowired
    private AdotanteRepository adotanteRepository;

    @Override
    public AdotanteRepository getRepository() {
        return adotanteRepository;
    }
}
