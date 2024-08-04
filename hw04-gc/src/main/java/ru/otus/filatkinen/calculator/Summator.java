package ru.otus.filatkinen.calculator;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"java:S125"})
public class Summator {
    private long sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private long someValue = 0;
    private long someValueLast = 0;
    private final List<Data> listValues = new ArrayList<>(100_000);

    // !!! сигнатуру метода менять нельзя
    public void calc(Data data) {
        listValues.add(data);
        if (listValues.size() % 100_000 == 0) {
            listValues.clear();
            someValueLast = 0;
        }
        sum += data.getValue();

        sumLastThreeValues = data.getValue() + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = data.getValue();

        if (!listValues.isEmpty()) {
            someValueLast += listValues.getLast().getValue();
        }

        for (int idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (data.getValue() + 1) - sum);
            someValue = Math.abs(someValue) + listValues.size() + someValueLast;
        }
    }

    public long getSum() {
        return sum;
    }

    public long getPrevValue() {
        return prevValue;
    }

    public long getPrevPrevValue() {
        return prevPrevValue;
    }

    public long getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public long getSomeValue() {
        return someValue;
    }
}
