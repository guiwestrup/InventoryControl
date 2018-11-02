package br.org.catolicasc.model;

public enum Type {
    //TODO fazer os outros tipos
    INSERTWITHDRAW(1),INVOICEENTRIE(2);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
