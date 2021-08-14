package co.com.mercadolibre.xmen.service;

public interface ISearchSequence {

    int getSequences(String[] dna);
    int getHorizontal(String[] dna);
    int getVertical(String[] dna);
    int getDiagonal(String[] dna);
    int getInverseDiagonal(String[] dna);

}
