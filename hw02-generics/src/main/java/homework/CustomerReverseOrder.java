package homework;

import java.util.ArrayDeque;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final ArrayDeque<Customer> arrayDeque;

    public CustomerReverseOrder() {
        arrayDeque = new ArrayDeque<>();
    }

    public void add(Customer customer) {
        arrayDeque.push(customer);
    }

    public Customer take() {
        return arrayDeque.pop();
    }
}
