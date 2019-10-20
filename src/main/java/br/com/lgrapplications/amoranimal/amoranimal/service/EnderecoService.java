package br.com.lgrapplications.amoranimal.amoranimal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EnderecoService {

    public String findDadosCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://viacep.com.br/ws/"+cep+"/json/");
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {});

        return response.getBody();
    }
}
