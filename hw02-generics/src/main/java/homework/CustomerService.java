package homework;

import java.util.*;

public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> customerStringTreeMap;

    public CustomerService() {
        customerStringTreeMap = new TreeMap<>(
                Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> minEntry = customerStringTreeMap.firstEntry();
        if (minEntry != null) {
            return new AbstractMap.SimpleEntry<>(minEntry.getKey().ShallowCopy(), minEntry.getValue());
        }
        return null;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = customerStringTreeMap.higherEntry(customer);
        if (nextEntry != null) {
            return new AbstractMap.SimpleEntry<>(nextEntry.getKey().ShallowCopy(), nextEntry.getValue());
        }
        return null;
    }

    public void add(Customer customer, String data) {
        customerStringTreeMap.put(customer, data);
    }
}
