package core.ia.strategy.choose;

import util.Util;

import java.util.Arrays;
import java.util.List;

public class ChooseRandom {

    private final static ChooseRandom instance = new ChooseRandom();

    private ChooseRandom() {
        // Singleton
    }

    public static ChooseRandom getInstance() {
        return instance;
    }

    public Object chooseRandom(List<?> list) {
        if (!list.isEmpty()) {
            return list.get(Util.getNextInt(list.size()));
        }
        return null;
    }

    public Object chooseRandom(Object[] list) {
        return chooseRandom(Arrays.asList(list));
    }

    public boolean chooseRandom() {
        return Util.rand.nextBoolean();
    }

    public int chooseRandom(int min, int max) {
        return Util.rand.nextInt(min, max + 1);
    }
}
