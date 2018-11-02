package br.org.catolicasc.model;

public enum Role {
    ADMIN(1), USER(2);

    private final int value;

    Role(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
