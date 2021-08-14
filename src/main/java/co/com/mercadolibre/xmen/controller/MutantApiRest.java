package co.com.mercadolibre.xmen.controller;

import co.com.mercadolibre.xmen.model.Dna;
import co.com.mercadolibre.xmen.service.IMutant;
import co.com.mercadolibre.xmen.service.implementation.MutantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
public class MutantApiRest {

    private final IMutant mutantService = new MutantService();

    @PostMapping(path = "/mutant")
    public ResponseEntity isMutant(@RequestBody Dna dna) {
        return mutantService.isMutant(dna.getDna());
    }

}
