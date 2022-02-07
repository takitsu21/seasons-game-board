package core.ia.strategy.choose;

import core.ia.Player;

import java.util.List;

public interface Strategy {

    Object choose(Player player);
    Object doChoose(Player player);
    Object doTheChoose(Player player);

}
