package br.com.lgrapplications.amoranimal.amoranimal.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeocodeApiService {

    @Autowired
    private GeoApiContext geoApiContext;

    public GeocodingResult findCoordernadaByEndereco(String endereco)  {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(geoApiContext,
                    endereco).await();

            if (results != null && results.length > 0) {
                return results[0];
            }
            return null;
        }catch (Exception ex){
            try {
                Thread.currentThread().sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return findCoordernadaByEndereco(endereco);
        }
    }

}
