package co.com.mercadolibre.xmen.service;

import org.springframework.http.ResponseEntity;

public interface IMutant {

    ResponseEntity isMutant(String[] dna);

}
