package br.com.lgrapplications.amoranimal.amoranimal.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeocodeConfig {

    @Value("${geocode.api.apikey}")
    private String geocodeApiApikey;

    @Bean
    public GeoApiContext geoApiContext(){ return new GeoApiContext.Builder()
            .apiKey(geocodeApiApikey)
            .build();}
}
