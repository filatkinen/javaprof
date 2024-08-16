package ru.otus.filatkinen.atm;

public class ExceptionAtmCannotGiveMoney extends Exception {
    public ExceptionAtmCannotGiveMoney(String message) {
        super(message);
    }
}
