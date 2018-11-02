package br.org.catolicasc.model;

public enum Status {
    INATIVE(0), ACTIVE(1);

    private final int value;

    Status (int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
