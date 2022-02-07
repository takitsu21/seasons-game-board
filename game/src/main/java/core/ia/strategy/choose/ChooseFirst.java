package core.ia.strategy.choose;

import java.util.Arrays;
import java.util.List;

public class ChooseFirst {

    private final static ChooseFirst instance = new ChooseFirst();

    private ChooseFirst() {

    }

    public static ChooseFirst getInstance() {
        return instance;
    }

    public Object chooseFirst(List<?> list) {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public Object chooseFirst(Object[] list) {
        return chooseFirst(Arrays.asList(list));
    }

    public boolean chooseFirst() {
        return true;
    }

}
