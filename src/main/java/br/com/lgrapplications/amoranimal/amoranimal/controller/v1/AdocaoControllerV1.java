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
import java.util.stream.Collectors;

@RestController
@RequestMapping("rs/v1/adocoes")
public class AdocaoControllerV1 extends DefaultController<Adocao> {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private AdotanteRepository adotanteRepository;

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping("salvar")
    public Adocao salvar(@RequestBody Adocao adocao){
        LocalDateTime hoje = LocalDateTime.now();
        Adotante adotante = null;
        Animal animal = null;

        if (adocao.getId() == null){
            adocao.setDtAdocao(hoje);
            adotante = adotanteRepository.findById(adocao.getAdotante().getId()).get();
            animal = animalRepository.findById(adocao.getAnimal().getId()).get();
            adotante.setDtAdocao(hoje);
            animal.setDtAdocao(hoje);

            animal.setNomeAdotante(adotante.getNome());
            adotante.setNomeAnimal(animal.getNome());
            adotante.setTpAnimal(animal.getTpAnimal());
        } else {
            Mensagem ocorrencia = mensagemService.findNewMensagem(adocao.getOcorrencias());
            Mensagem visita = mensagemService.findNewMensagem(adocao.getVisitas());
            Mensagem devolucao = mensagemService.findNewMensagem(adocao.getDevolucoes());

            if (ocorrencia != null
                    || visita != null
                    || devolucao != null){

                adotante = adotanteRepository.findById(adocao.getAdotante().getId()).get();
                if (ocorrencia != null){
                    adotante.getOcorrencias().add((Ocorrencia) ocorrencia);
                }else if (visita != null){
                    adotante.getVisitas().add((Visita)visita);
                }else if (devolucao != null){
                    adotante.getDevolucoes().add((Devolucao)devolucao);
                }

                animal = animalRepository.findById(adocao.getAnimal().getId()).get();
                if (ocorrencia != null){
                    animal.getOcorrencias().add((Ocorrencia) ocorrencia);
                }else if (visita != null){
                    animal.getVisitas().add((Visita)visita);
                }else if (devolucao != null){
                    animal.getDevolucoes().add((Devolucao)devolucao);
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
            }
        }
        if (adotante != null){
            adotanteRepository.save(adotante);
        }
        if (animal != null){
            animalRepository.save(animal);
        }
        return getRepository().save(adocao);
    }

    @GetMapping("ativo")
    public List<Adocao> findAllAtivos(){
        return adocaoRepository.findAll().stream().filter(ado ->{
            return ado.isIeAtivo();
        }).sorted((ado1,ado2) -> {
            return ado1.getDtUltAcomp() == null ? 1 :
                    ado2.getDtUltAcomp() == null ? -1 :
                            ado1.getDtUltAcomp().compareTo(ado2.getDtUltAcomp());
        }).collect(Collectors.toList());
    }

    @Override
    public AdocaoRepository getRepository() {
        return adocaoRepository;
    }
}
