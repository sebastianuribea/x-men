package co.com.mercadolibre.xmen.controller;

import co.com.mercadolibre.xmen.model.Dna;
import co.com.mercadolibre.xmen.service.IDna;
import co.com.mercadolibre.xmen.service.IMutant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
public class MutantApiRest {

    private final IMutant mutantService;
    private final IDna dnaService;

    public MutantApiRest(IMutant mutantService, IDna dnaService) {
        this.mutantService = mutantService;
        this.dnaService = dnaService;
    }

    @PostMapping(path = "/mutant")
    public ResponseEntity isMutant(@RequestBody Dna dna) {
        return mutantService.isMutant(dna.getDna());
    }

    @GetMapping(path = "/stats")
    public ResponseEntity stats() {
        return ResponseEntity.status(HttpStatus.OK).body(dnaService.getStatistics());
    }

}
