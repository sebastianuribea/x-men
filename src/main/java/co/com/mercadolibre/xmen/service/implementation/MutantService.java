package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.service.IDna;
import co.com.mercadolibre.xmen.service.IMutant;
import co.com.mercadolibre.xmen.service.ISearchSequence;
import co.com.mercadolibre.xmen.service.IValidatorDna;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements IMutant {

    private final IValidatorDna validatorDnaService;
    private final ISearchSequence searchSequenceService;
    private final IDna dnaService;

    public MutantService(IValidatorDna validatorDnaService, ISearchSequence searchSequenceService, IDna dnaService) {
        this.validatorDnaService = validatorDnaService;
        this.searchSequenceService = searchSequenceService;
        this.dnaService = dnaService;
    }

    @Override
    public ResponseEntity isMutant(String[] dna) {
        ResponseEntity responseEntity;
        if (validatorDnaService.isNotNullAndMinOrderFour(dna) && validatorDnaService.isSymmetrical(dna) && validatorDnaService.containsOnlyAllowedValues(dna)) {
            DnaEntity dnaEntityDb = dnaService.getDna(dna);
            if (dnaEntityDb != null) {
                responseEntity = (dnaEntityDb.isMutant()) ? ResponseEntity.status(HttpStatus.OK).body("Es mutante") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Es humano");
            } else if (searchSequenceService.getSequences(dna) > 1) {
                responseEntity = ResponseEntity.status(HttpStatus.OK).body("Es mutante");
                dnaService.saveDna(dna, true);
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Es humano");
                dnaService.saveDna(dna, false);
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El ADN enviado no cumple con las condiciones: \n-no ser null ni vacío. " +
                            "\n-ser simétrico y de orden mínimo 4. \n-No contener caracteres diferentes a (T,G,C,A)");
        }
        return responseEntity;
    }
}
