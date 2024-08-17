package ru.otus.filatkinen.atm;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoAtm {
    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger(DemoAtm.class);

        Atm atm = new Atm();
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(1, 100, 1000, 100));
        cells.add(new Cell(2, 100, 2000, 100));
        cells.add(new Cell(3, 100, 5000, 100));

        atm.loadCells(cells);
        log.info("ATM before:{}", atm);

        int[] money = {1000, 100, 5000, 4500, 2000, 3000, 6000};
        for (int moneyValue : money) {
            try {
                PackOfBanknots pack = atm.giveMoneyToCustomer(moneyValue);
                log.info("Give money={} , pack={}", moneyValue, pack);
            } catch (ExceptionAtmCannotGiveMoney e) {
                log.info("Cann't give money={}", moneyValue);
            }
        }

        log.info("ATM after:{}", atm);
    }
}
