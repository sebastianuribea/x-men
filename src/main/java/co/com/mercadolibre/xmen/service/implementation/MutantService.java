package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.service.IMutant;
import co.com.mercadolibre.xmen.service.ISearchSequence;
import co.com.mercadolibre.xmen.service.IValidatorDna;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MutantService implements IMutant {

    private final IValidatorDna validatorDnaService = new ValidatorDnaService();
    private final ISearchSequence searchSequenceService = new SearchSequenceService();

    @Override
    public ResponseEntity isMutant(String[] dna) {
        ResponseEntity responseEntity;
        if (validatorDnaService.isNotNullAndMinOrderFour(dna) && validatorDnaService.isSymmetrical(dna) && validatorDnaService.containsOnlyAllowedValues(dna)) {
            if(searchSequenceService.getSequences(dna)>1){
                responseEntity = ResponseEntity.status(HttpStatus.OK).body("Es mutante");
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Es humano");
            }
        }else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El ADN enviado no cumple con las condiciones: \n-no ser null ni vacío. " +
                            "\n-ser simétrico y de orden mínimo 4. \n-No contener caracteres diferentes a (T,G,C,A)");
        }
        return responseEntity;
    }
}
