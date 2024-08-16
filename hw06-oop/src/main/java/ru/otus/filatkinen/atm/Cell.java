package ru.otus.filatkinen.atm;

public class Cell {
    private int amount;
    private final int size;
    private final int banknote;
    private final int id;

    public Cell(int id, int size, int banknote, int amount) {
        this.id = id;
        this.size = size;
        this.banknote = banknote;
        this.amount = amount;
    }

    public void loadToCell(int number) throws ExceptionCellFull {
        if (number + amount > size) {
            int result = size - amount;
            amount = size;
            throw new ExceptionCellFull("Cell is Full", result);
        }
        amount += number;
    }

    public void unLoadToCell(int number) throws ExceptionCellEmpty {
        if (amount < number) {
            int result = amount;
            amount = 0;
            throw new ExceptionCellEmpty("Cell is Empty", result);
        }
        amount -= number;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getSize() {
        return size;
    }

    public int getBanknote() {
        return banknote;
    }

    @Override
    public String toString() {
        return "Cell{" + "amount=" + amount + ", size=" + size + ", banknote=" + banknote + ", id=" + id + '}';
    }
}
