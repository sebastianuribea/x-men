package co.com.mercadolibre.xmen.service;

public interface IMutant {

    int getHorizontalSequences(String[] dna);
    int getVerticalSequences(String[] dna);
    int getDiagonalSequences(String[] dna);
    int getInverseDiagonalSequences(String[] dna);

}
