package ru.otus.filatkinen.atm;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Atm {
    private List<Cell> cells;
    private Map<Integer, Integer> idx;

    private static final Logger log = LoggerFactory.getLogger(Atm.class);

    public Atm() {
        this.cells = new ArrayList<>();
        this.idx = new HashMap<>();
    }

    public void loadCells(List<Cell> cells) {
        this.cells.addAll(cells);
        this.cells.sort((o1, o2) -> Integer.compare(o2.getBanknote(), o1.getBanknote()));

        int idxCount = 0;
        for (Cell cell : this.cells) {
            idx.put(cell.getBanknote(), idxCount);
            idxCount++;
        }
    }

    public void unloadCells() {
        cells.clear();
        idx.clear();
    }

    /**
     * @param pack
     * @return banknots, were not fitted to cells, or  if fitted
     */
    public PackOfBanknots takeMoneyFromCustomer(PackOfBanknots pack) {
        List<Integer> banknots = new ArrayList<>(pack.getPack().keySet());
        banknots.sort(Collections.reverseOrder());

        PackOfBanknots notFitted = new PackOfBanknots.Builder().build();

        for (Integer banknot : banknots) {
            int amount = pack.getPack().get(banknot);
            Cell cell = cells.get(idx.get(banknot));
            if (cell == null) {
                log.info("Cell not found for banknot= {} ", banknot);
            } else {
                try {
                    cell.loadToCell(amount);
                } catch (ExceptionCellFull exceptionCellFull) {
                    int left = exceptionCellFull.getNumberLoaded();
                    notFitted.add(banknot, left);
                }
            }
        }
        return notFitted.getPack().isEmpty() ? null : notFitted;
    }

    /**
     * @param money
     * @return pack of banknots
     * @throws ExceptionAtmCannotGiveMoney
     */
    public PackOfBanknots giveMoneyToCustomer(int money) throws ExceptionAtmCannotGiveMoney {
        if (money == 0) {
            throw new ExceptionAtmCannotGiveMoney("Cannot give money");
        }

        PackOfBanknots give = new PackOfBanknots.Builder().build();

        int left = money;
        for (Cell cell : cells) {
            int banknote = cell.getBanknote();
            int amount = left / banknote;

            try {
                cell.unLoadToCell(amount);
            } catch (ExceptionCellEmpty e) {
                amount = e.getNumberUnLoaded();
            }

            left = left - amount * banknote;
            if (amount != 0) {
                give.add(banknote, amount);
            }
            if (left == 0) {
                break;
            }
        }
        if (left != 0) {
            throw new ExceptionAtmCannotGiveMoney("Cannot give money");
        }
        return give;
    }

    @Override
    public String toString() {
        return "Atm{" + "cells=" + cells + '}';
    }
}
