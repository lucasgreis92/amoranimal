package br.com.lgrapplications.amoranimal.amoranimal.repository;

import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal,String> {
}
