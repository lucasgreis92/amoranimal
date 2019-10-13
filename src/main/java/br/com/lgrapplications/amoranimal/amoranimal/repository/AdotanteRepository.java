package br.com.lgrapplications.amoranimal.amoranimal.repository;

import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Adotante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdotanteRepository extends MongoRepository<Adotante,String> {
}
