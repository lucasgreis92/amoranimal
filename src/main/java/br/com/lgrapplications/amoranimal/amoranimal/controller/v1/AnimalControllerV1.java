package br.com.lgrapplications.amoranimal.amoranimal.controller.v1;

import br.com.lgrapplications.amoranimal.amoranimal.base.DefaultController;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Animal;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rs/v1/animais")
public class AnimalControllerV1  extends DefaultController<Animal> {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public AnimalRepository getRepository() {
        return animalRepository;
    }
}
