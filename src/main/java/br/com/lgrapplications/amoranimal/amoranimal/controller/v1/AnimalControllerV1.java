package br.com.lgrapplications.amoranimal.amoranimal.controller.v1;

import br.com.lgrapplications.amoranimal.amoranimal.base.DefaultController;
import br.com.lgrapplications.amoranimal.amoranimal.model.Devolucao;
import br.com.lgrapplications.amoranimal.amoranimal.model.Mensagem;
import br.com.lgrapplications.amoranimal.amoranimal.model.Ocorrencia;
import br.com.lgrapplications.amoranimal.amoranimal.model.Visita;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Adocao;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Adotante;
import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Animal;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AdocaoRepository;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AdotanteRepository;
import br.com.lgrapplications.amoranimal.amoranimal.repository.AnimalRepository;
import br.com.lgrapplications.amoranimal.amoranimal.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rs/v1/animais")
public class AnimalControllerV1  extends DefaultController<Animal> {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private AdotanteRepository adotanteRepository;

    @Autowired
    private MensagemService mensagemService;

    @PostMapping("salvar")
    public Animal salvar(@RequestBody Animal animal){
        if (animal.getId() != null){
            Optional<Adocao> adocaoOp = adocaoRepository.findAll().stream().filter(ado ->{
                return ado.getAnimal().getId().equalsIgnoreCase(animal.getId())
                        && ado.isIeAtivo();
            }).findFirst();
            LocalDateTime hoje = LocalDateTime.now();
            if (adocaoOp.isPresent()){
                Adocao adocao = adocaoOp.get();
                Mensagem ocorrencia = mensagemService.findNewMensagem(animal.getOcorrencias());
                Mensagem visita = mensagemService.findNewMensagem(animal.getVisitas());
                Mensagem devolucao = mensagemService.findNewMensagem(animal.getDevolucoes());

                if (ocorrencia != null
                        || visita != null
                        || devolucao != null) {

                    if (ocorrencia != null){
                        adocao.getOcorrencias()
                                .add((Ocorrencia) ocorrencia);
                    }else if (visita != null){
                        adocao.getVisitas()
                                .add((Visita)visita);
                    }else if (devolucao != null){
                        adocao.getDevolucoes()
                                .add((Devolucao)devolucao);
                    }

                    Adotante adotante = adotanteRepository.findById(adocao.getAdotante().getId()).get();
                    if (ocorrencia != null){
                        adotante.getOcorrencias().add((Ocorrencia) ocorrencia);
                    }else if (visita != null){
                        adotante.getVisitas().add((Visita)visita);
                    }else if (devolucao != null){
                        adotante.getDevolucoes().add((Devolucao)devolucao);
                    }

                    adotante.setDtUltAcomp(hoje);
                    animal.setDtUltAcomp(hoje);
                    adocao.setDtUltAcomp(hoje);
                    if (devolucao != null){
                        adocao.setIeAtivo(false);
                        adotante.setDtAdocao(null);
                        animal.setDtAdocao(null);
                        animal.setNomeAdotante(null);
                        adotante.setNomeAnimal(null);
                        adotante.setTpAnimal(null);
                    }
                    adocao.setAnimal(animal);
                    adocao.setAdotante(adotante);
                    adocaoRepository.save(adocao);
                    adotanteRepository.save(adotante);
                }

            }

        }


        return getRepository().save(animal);
    }

    @GetMapping("ativo")
    public List<Animal> findAllAtivos(){
        return animalRepository.findAll().stream().filter(a ->{
            return a.getNomeAdotante() == null;
        }).sorted((a1,a2)->{
            return a2.getDtCadastramento().compareTo(a1.getDtCadastramento());
        }).collect(Collectors.toList());
    }

    @Override
    public AnimalRepository getRepository() {
        return animalRepository;
    }
}
