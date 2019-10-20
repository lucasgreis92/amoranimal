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
import br.com.lgrapplications.amoranimal.amoranimal.service.EnderecoService;
import br.com.lgrapplications.amoranimal.amoranimal.service.GeocodeApiService;
import br.com.lgrapplications.amoranimal.amoranimal.service.MensagemService;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rs/v1/adotantes")
public class AdotanteControllerV1 extends DefaultController<Adotante> {

    @Autowired
    private AdotanteRepository adotanteRepository;

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GeocodeApiService geocodeApiService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("salvar")
    public Adotante salvar(@RequestBody Adotante adotante){
        if (adotante.getLocation() == null){
            String endereco = adotante.getLogradouro() + ", " +
                    adotante.getNumero() + ", " +
                    adotante.getBairro() + ", " +
                    adotante.getCep() + " - " +
                    adotante.getCidade();
            GeocodingResult geocodingResult = geocodeApiService.findCoordernadaByEndereco(endereco);
            adotante.setLocation(new GeoJsonPoint(Double.valueOf(geocodingResult.geometry.location.lng),Double.valueOf(geocodingResult.geometry.location.lat)));
        }
        if (adotante.getId() != null){
            Optional<Adocao> adocaoOp = adocaoRepository.findAll().stream().filter(ado ->{
                return ado.getAdotante().getId().equalsIgnoreCase(adotante.getId())
                        && ado.isIeAtivo();
            }).findFirst();
            LocalDateTime hoje = LocalDateTime.now();
            if (adocaoOp.isPresent()){
                Adocao adocao = adocaoOp.get();
                Mensagem ocorrencia = mensagemService.findNewMensagem(adotante.getOcorrencias());
                Mensagem visita = mensagemService.findNewMensagem(adotante.getVisitas());
                Mensagem devolucao = mensagemService.findNewMensagem(adotante.getDevolucoes());

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

                    Animal animal = animalRepository.findById(adocao.getAnimal().getId()).get();
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
                    adocaoRepository.save(adocao);
                    adotanteRepository.save(adotante);
                }

            }

        }


        return getRepository().save(adotante);
    }

    @GetMapping("ativo")
    public List<Adotante>  findAllAtivos(){
        return adotanteRepository.findAll().stream().filter(ado ->{
            return ado.getNomeAnimal() == null;
        }).collect(Collectors.toList());
    }

    @GetMapping("cep/{cep}")
    public String findDadosCep(@PathVariable("cep") String cep){
        return enderecoService.findDadosCep(cep);
    }

    @Override
    public AdotanteRepository getRepository() {
        return adotanteRepository;
    }
}
