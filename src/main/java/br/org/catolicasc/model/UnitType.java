package br.org.catolicasc.model;

public enum UnitType {
    //unidades de medidas https://docs.google.com/spreadsheets/d/1iOO86IEGefkgmCfooWU1_fsit0OY5h7EDsnmWFT-iWg/edit?usp=sharing
    AMPOLA(5), CX(6), EMBALAGEM(7), FARDO(8), FRASCO(9), GALAO(10), KG(2), LITRO(11), M(3), PACOTE(12), PC(13), SACO(4), UN(1);

    private final int value;

    UnitType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
