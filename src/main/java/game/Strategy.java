package game;


import java.util.ArrayList;

/**
 * An abstract implementation of one strategy
 */
public abstract class Strategy implements Comparable<Strategy>
{
    /** The game on which this strategy is applied */
    protected Game game;

    /**
     * Basic setter for the game
     * @param game
     * The game to which this strategy refers
     */
    void useGame(Game game)
    {
        this.game = game;
    }

    /**
     * The core method which is abstract
     * @param initial
     * The initial state of the strategy
     * @return
     * An array list representing the solution after applying the strategy
     */
    public abstract ArrayList<State> run(State initial);

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Strategy strategy)
    {
        String left = toString();
        String right = strategy.toString();
        return left.compareTo(right);
    }
}