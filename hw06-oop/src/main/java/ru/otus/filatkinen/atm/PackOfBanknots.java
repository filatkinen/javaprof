package ru.otus.filatkinen.atm;

import java.util.HashMap;
import java.util.Map;

public class PackOfBanknots {
    private final Map<Integer, Integer> pack;

    private PackOfBanknots(Builder builder) {
        this.pack = builder.pack;
    }

    public void add(int banknote, int amount) {
        pack.put(banknote, amount);
    }

    public static class Builder {
        private final Map<Integer, Integer> pack = new HashMap<>();

        public void add(int banknote, int amount) {
            pack.put(banknote, amount);
        }

        public PackOfBanknots build() {
            return new PackOfBanknots(this);
        }
    }

    @Override
    public String toString() {
        return "PackOfBanknots{" + "pack=" + pack + '}';
    }

    public Map<Integer, Integer> getPack() {
        return pack;
    }
}
