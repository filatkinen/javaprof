package ru.otus.filatkinen.atm;

public class ExceptionCellFull extends Exception {
    private final int numberLoaded;

    public ExceptionCellFull(String message, int numberLoaded) {
        super(message);
        this.numberLoaded = numberLoaded;
    }

    public int getNumberLoaded() {
        return numberLoaded;
    }
}
