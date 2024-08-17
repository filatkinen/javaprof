package ru.otus.filatkinen.atm;

public class ExceptionCellEmpty extends Exception {
    private final int numberUnLoaded;

    public ExceptionCellEmpty(String message, int numberUnLoaded) {
        super(message);
        this.numberUnLoaded = numberUnLoaded;
    }

    public int getNumberUnLoaded() {
        return numberUnLoaded;
    }
}
