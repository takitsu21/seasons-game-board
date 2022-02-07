package core.cards.comparator;

import core.dice.Dice;
import core.ia.Player;

import java.util.Comparator;

public class SizeCardOnBoardPlayerComparator implements Comparator<Player> {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * The implementor must ensure that {@link Integer#signum
     * signum}{@code (compare(x, y)) == -signum(compare(y, x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * compare(x, y)} must throw an exception if and only if {@code
     * compare(y, x)} throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.<p>
     * <p>
     * Finally, the implementor must ensure that {@code compare(x,
     * y)==0} implies that {@code signum(compare(x,
     * z))==signum(compare(y, z))} for all {@code z}.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     * @apiNote It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     */
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getFacadeIA().getCardOnBoard().size() < o2.getFacadeIA().getCardOnBoard().size()) {
            return -1;
        } else if (o1.getFacadeIA().getCardOnBoard().size() > o2.getFacadeIA().getCardOnBoard().size()) {
            return 1;
        }
        return 0;
    }
}
