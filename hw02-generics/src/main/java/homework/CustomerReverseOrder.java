package homework;

import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final SortedSet<Customer> set;

    public CustomerReverseOrder() {
        set = new TreeSet<>((c1, c2) ->
                Long.compare(c2.getScores(), c1.getScores())
        );
    }

    public void add(Customer customer) {
        set.add(customer);
    }

    public Customer take() {
        return set.removeFirst();
    }
}
