package br.org.catolicasc.model;

public enum UnitType {
    AMPOLA(1),
    CX(2),
    EMBALAGEM(3),
    FARDO(4),
    FRASCO(5),
    GALAO(6),
    KG(7),
    LITRO(8),
    M(9),
    PACOTE(10),
    PC(11),
    SACO(12),
    UN(13);

    private final int value;

    UnitType(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
